package sorisoop.soridam.infra.notification;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.favoriteplace.application.FavoritePlaceQueryService;
import sorisoop.soridam.domain.notification.application.NotificationCommandService;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.infra.notification.sse.NotificationSsePayload;
import sorisoop.soridam.infra.notification.sse.SseEmitterManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationAsyncService {
	private final NotificationCommandService notificationCommandService;
	private final FavoritePlaceQueryService favoritePlaceQueryService;
	private final SseEmitterManager sseEmitterManager;

	@Async
	public void sendReviewNotification(ReviewCreatedEvent event) {
		List<Notification> notifications = favoritePlaceQueryService.findTargetsByPlaceIdExcludingUser(event.placeId(),
				event.writerId()).stream()
			.map(favoritePlace -> {
				Long userId = favoritePlace.userId();
				Long placeId = favoritePlace.placeId();
				String placeName = favoritePlace.placeName();
				String content = String.format("즐겨찾기한 장소 %s에 새로운 리뷰가 등록되었습니다.", placeName);
				return Notification.createReviewNotification(userId, placeId, content);
			})
			.toList();
		notificationCommandService.createNotifications(notifications);

		notifications.forEach(notification -> {
			NotificationSsePayload payload = NotificationSsePayload.from(notification);
			sseEmitterManager.sendToUser(notification.getReceiverId(), payload);
		});
	}
}
