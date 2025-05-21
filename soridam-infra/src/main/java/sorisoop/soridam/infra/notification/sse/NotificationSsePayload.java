package sorisoop.soridam.infra.notification.sse;

import java.time.LocalDateTime;

import lombok.Builder;
import sorisoop.soridam.domain.notification_service.domain.Notification;
import sorisoop.soridam.domain.notification_service.domain.enums.NotificationType;

@Builder
public record NotificationSsePayload(
	NotificationType type,
	String content,
	Long targetId,
	LocalDateTime createdAt
) {
	public static NotificationSsePayload from(Notification notification) {
		return NotificationSsePayload.builder()
			.type(notification.getType())
			.content(notification.getContent())
			.targetId(notification.getPlaceId())
			.createdAt(notification.getCreatedAt())
			.build();
	}
}

