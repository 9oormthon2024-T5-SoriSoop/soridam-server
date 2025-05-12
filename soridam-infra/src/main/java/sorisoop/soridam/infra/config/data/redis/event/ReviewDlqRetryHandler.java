package sorisoop.soridam.infra.config.data.redis.event;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.favoriteplace.application.FavoritePlaceQueryService;
import sorisoop.soridam.domain.notification.application.NotificationCommandService;
import sorisoop.soridam.domain.notification.domain.Notification;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewDlqRetryHandler {
	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper;
	private final FavoritePlaceQueryService favoritePlaceQueryService;
	private final NotificationCommandService notificationCommandService;

	private static final String DLQ_STREAM_KEY = "stream:review_dead";
	private static final String PROCESSED_KEY = "processed:review_created";
	private static final int MAX_RETRY = 5;
	private final Map<String, Integer> retryCountMap = new ConcurrentHashMap<>();

	@Scheduled(fixedDelay = 60000)
	public void retryDeadLetters() {
		List<MapRecord<String, Object, Object>> records = fetchDlqRecords();
		for (MapRecord<String, Object, Object> record : records) {
			retryRecord(record);
		}
	}

	private List<MapRecord<String, Object, Object>> fetchDlqRecords() {
		return redisTemplate.opsForStream().range(DLQ_STREAM_KEY, Range.unbounded());
	}

	private void retryRecord(MapRecord<String, Object, Object> record) {
		try {
			String json = (String) record.getValue().get("event");
			ReviewCreatedEvent event = objectMapper.readValue(json, ReviewCreatedEvent.class);
			String reviewIdKey = event.reviewId().toString();

			if (isAlreadyProcessed(reviewIdKey, record)) return;
			if (isMaxRetryExceeded(reviewIdKey, record)) return;

			List<Notification> notifications = createNotifications(event);
			notificationCommandService.createNotifications(notifications);
			redisTemplate.opsForSet().add(PROCESSED_KEY, reviewIdKey);
			redisTemplate.opsForStream().delete(DLQ_STREAM_KEY, record.getId());
			retryCountMap.remove(reviewIdKey);
			log.info("DLQ 메시지 {} 재처리 성공", reviewIdKey);

		} catch (Exception e) {
			handleRetryFailure(record, e);
		}
	}

	private boolean isAlreadyProcessed(String reviewIdKey, MapRecord<String, Object, Object> record) {
		if (Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(PROCESSED_KEY, reviewIdKey))) {
			log.info("DLQ 메시지 {} 는 이미 처리됨", reviewIdKey);
			redisTemplate.opsForStream().delete(DLQ_STREAM_KEY, record.getId());
			retryCountMap.remove(reviewIdKey);
			return true;
		}
		return false;
	}

	private boolean isMaxRetryExceeded(String reviewIdKey, MapRecord<String, Object, Object> record) {
		int retry = retryCountMap.getOrDefault(reviewIdKey, 0);
		if (retry >= MAX_RETRY) {
			log.warn("DLQ 메시지 {} 재시도 {}회 초과, 삭제합니다.", reviewIdKey, retry);
			redisTemplate.opsForStream().delete(DLQ_STREAM_KEY, record.getId());
			retryCountMap.remove(reviewIdKey);
			return true;
		}
		retryCountMap.put(reviewIdKey, retry + 1);
		return false;
	}

	private List<Notification> createNotifications(ReviewCreatedEvent event) {
		return favoritePlaceQueryService.findByPlaceId(event.placeId()).stream()
			.filter(fp -> !fp.getUser().getId().equals(event.writerId()))
			.map(fp -> Notification.createReviewNotification(
				fp.getUser().getId(),
				fp.getPlace().getId(),
				String.format("즐겨찾기한 장소 %s에 새로운 리뷰가 등록되었습니다.", fp.getPlace().getPlaceName())
			)).toList();
	}

	private void handleRetryFailure(MapRecord<String, Object, Object> record, Exception e) {
		try {
			String json = (String) record.getValue().get("event");
			ReviewCreatedEvent event = objectMapper.readValue(json, ReviewCreatedEvent.class);
			String reviewIdKey = event.reviewId().toString();
			log.error("DLQ 재처리 실패 ({}회): {}", retryCountMap.getOrDefault(reviewIdKey, 0), reviewIdKey, e);
		} catch (Exception parseEx) {
			log.error("DLQ 메시지 JSON 파싱 실패: {}", record.getId(), parseEx);
		}
	}
}
