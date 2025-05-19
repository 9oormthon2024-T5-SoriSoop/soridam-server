package sorisoop.soridam.infra.notification;

import java.util.List;
import java.util.Objects;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.favoriteplace.application.FavoritePlaceQueryService;
import sorisoop.soridam.domain.notification.application.NotificationCommandService;
import sorisoop.soridam.domain.notification.domain.Notification;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationAsyncService {
	private final NotificationCommandService notificationCommandService;
	private final FavoritePlaceQueryService favoritePlaceQueryService;

	@Async
	public void sendReviewNotification(ReviewCreatedEvent event) {
		try {
			List<Notification> notifications = favoritePlaceQueryService.findByPlaceId(event.placeId()).stream()
				.filter(fp -> !Objects.equals(fp.getUser().getId(), event.writerId())) // 본인 제외
				.map(favoritePlace -> {
					Long userId = favoritePlace.getUser().getId();
					Long placeId = favoritePlace.getPlace().getId();
					String placeName = favoritePlace.getPlace().getPlaceName();
					String content = String.format("즐겨찾기한 장소 %s에 새로운 리뷰가 등록되었습니다.", placeName);
					return Notification.createReviewNotification(userId, placeId, content);
				})
				.toList();
			notificationCommandService.createNotifications(notifications);
		} catch (Exception e) {
			log.error("리뷰 알림 전송 중 오류 발생: {}", e.getMessage(), e);
		}
	}
}
