package sorisoop.soridam.infra.config.data.redis.event;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.favoriteplace.application.FavoritePlaceQueryService;
import sorisoop.soridam.domain.notification.application.NotificationCommandService;
import sorisoop.soridam.domain.notification.domain.Notification;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewNotificationConsumer {
	private final FavoritePlaceQueryService favoritePlaceQueryService;
	private final NotificationCommandService notificationCommandService;
	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper;

	private static final String STREAM_KEY = "stream:review_created";
	private static final String DLQ_STREAM_KEY = "stream:review_dead";
	private static final String GROUP = "review_created_group";
	private static final String CONSUMER = UUID.randomUUID().toString();
	private volatile boolean running = true;

	@PostConstruct
	public void init() {
		try {
			redisTemplate.opsForStream().createGroup(STREAM_KEY, GROUP);
		} catch (Exception ignored) {
			// 그룹이 이미 존재할 경우 예외 무시
		}
		new Thread(this::consumeLoop).start();
	}

	@PreDestroy
	public void shutdown() {
		running = false;
	}

	private void consumeLoop() {
		while (running) {
			if (Thread.currentThread().isInterrupted()) {
				log.warn("인터럽트 감지: Redis Stream 소비 루프 종료");
				break;
			}
			try {
				List<MapRecord<String, Object, Object>> messages = readMessages();
				if (messages != null) {
					for (MapRecord<String, Object, Object> message : messages) {
						processMessage(message);
					}
				}
			} catch (IllegalStateException e) {
				if (e.getMessage().contains("STOPPING")) break;
				log.error("Redis Stream 소비 중 에러 발생: {}", e.getMessage(), e);
			} catch (Exception e) {
				log.error("Redis Stream 소비 중 에러 발생: {}", e.getMessage(), e);
			}
		}
	}

	private List<MapRecord<String, Object, Object>> readMessages() {
		return redisTemplate.opsForStream().read(
			Consumer.from(GROUP, CONSUMER),
			StreamReadOptions.empty().count(10).block(Duration.ofSeconds(2)),
			StreamOffset.create(STREAM_KEY, ReadOffset.lastConsumed())
		);
	}

	private void processMessage(MapRecord<String, Object, Object> message) {
		try {
			String json = (String) message.getValue().get("event");
			ReviewCreatedEvent event = objectMapper.readValue(json, ReviewCreatedEvent.class);

			String processedKey = "processed:review_created";
			String reviewIdKey = event.reviewId().toString();
			if (Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(processedKey, reviewIdKey))) {
				log.info("이미 처리된 리뷰 이벤트: {}", reviewIdKey);
				redisTemplate.opsForStream().acknowledge(STREAM_KEY, GROUP, message.getId());
				return;
			}

			sendNotifications(event);
			redisTemplate.opsForSet().add(processedKey, reviewIdKey);
			redisTemplate.opsForStream().acknowledge(STREAM_KEY, GROUP, message.getId());
			log.info("리뷰 이벤트 {} 알림 전송 완료", reviewIdKey);

		} catch (Exception e) {
			handleProcessingFailure(message, e);
		}
	}

	private void sendNotifications(ReviewCreatedEvent event) {
		List<Notification> notifications = favoritePlaceQueryService.findByPlaceId(event.placeId()).stream()
			.filter(fp -> !Objects.equals(fp.getUser().getId(), event.writerId()))
			.map(favoritePlace -> {
				Long userId = favoritePlace.getUser().getId();
				Long placeId = favoritePlace.getPlace().getId();
				String placeName = favoritePlace.getPlace().getPlaceName();
				String content = String.format("즐겨찾기한 장소 %s에 새로운 리뷰가 등록되었습니다.", placeName);
				return Notification.createReviewNotification(userId, placeId, content);
			})
			.toList();
		notificationCommandService.createNotifications(notifications);
	}

	private void handleProcessingFailure(MapRecord<String, Object, Object> message, Exception e) {
		try {
			String json = (String) message.getValue().get("event");
			ReviewCreatedEvent event = objectMapper.readValue(json, ReviewCreatedEvent.class);
			String reviewIdKey = event.reviewId().toString();
			log.error("리뷰 이벤트 {} 처리 중 에러 발생, DLQ로 이동합니다.", reviewIdKey, e);
			redisTemplate.opsForStream().add(DLQ_STREAM_KEY, message.getValue());
			redisTemplate.opsForStream().acknowledge(STREAM_KEY, GROUP, message.getId());
		} catch (Exception ex) {
			log.error("DLQ 이동 중 JSON 파싱 실패: {}", message.getId(), ex);
		}
	}
}