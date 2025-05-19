package sorisoop.soridam.infra.notification;

import java.time.LocalDateTime;

import lombok.Builder;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.domain.notification.domain.enums.NotificationType;

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
			.createdAt(LocalDateTime.now())
			.build();
	}
}

