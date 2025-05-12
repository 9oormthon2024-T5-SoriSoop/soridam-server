package sorisoop.soridam.infra.config.data.redis.event;

import java.time.Duration;
import java.util.List;

import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.favoriteplace.application.FavoritePlaceQueryService;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.domain.notification.application.NotificationCommandService;

@Component
@RequiredArgsConstructor
public class ReviewNotificationConsumer {
	private final FavoritePlaceQueryService favoritePlaceQueryService;
	private final NotificationCommandService notificationCommandService;
	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper;

	private static final String STREAM_KEY = "stream:review_created";
	private static final String GROUP = "review_created_group";
	private static final String CONSUMER = "review_consumer_1";

	@PostConstruct
	public void init() {
		try {
			redisTemplate.opsForStream().createGroup(STREAM_KEY, GROUP);
		} catch (Exception ignored) {

		}
		new Thread(this::consumeLoop);
	}

	private void consumeLoop() {
		while (true) {
			try {
				List<MapRecord<String, Object, Object>> messages = redisTemplate.opsForStream().read(
					Consumer.from(GROUP, CONSUMER),
					StreamReadOptions.empty().count(10).block(Duration.ofSeconds(5)),
					StreamOffset.create(STREAM_KEY, ReadOffset.lastConsumed())
				);

				if (messages != null) {
					for (MapRecord<String, Object, Object> message : messages) {
						String json = (String) message.getValue().get("event");
						ReviewCreatedEvent event = objectMapper.readValue(json, ReviewCreatedEvent.class);

						List<Notification> notifications = favoritePlaceQueryService.findByPlaceId(event.placeId()).stream()
							.map(favoritePlace -> {
								Long userId = favoritePlace.getUser().getId();
								Long placeId = favoritePlace.getPlace().getId();
								String placeName = favoritePlace.getPlace().getPlaceName();
								String content = String.format("즐겨찾기한 장소 [%s]에 새로운 리뷰가 등록되었습니다.", placeName);
								return Notification.createReviewNotification(userId, placeId, content);
							})
							.toList();

						notificationCommandService.createNotifications(notifications);
						redisTemplate.opsForStream().acknowledge(STREAM_KEY, GROUP, message.getId());
					}
				}
			} catch (Exception e) {
				// 로그 처리
			}
		}
	}

}
