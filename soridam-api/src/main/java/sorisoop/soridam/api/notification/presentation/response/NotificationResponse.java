package sorisoop.soridam.api.notification.presentation.response;

import java.time.LocalDateTime;

import lombok.Builder;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.domain.notification.domain.enums.NotificationType;

@Builder
public record NotificationResponse(
	Long id,
	Long placeId,
	String content,
	NotificationType notificationType,
	boolean isRead,
	LocalDateTime createdAt
) {
	public static NotificationResponse from(Notification notification) {
		return NotificationResponse.builder()
			.id(notification.getId())
			.placeId(notification.getPlaceId())
			.content(notification.getContent())
			.notificationType(notification.getType())
			.isRead(notification.isRead())
			.createdAt(notification.getCreatedAt())
			.build();
	}
}
