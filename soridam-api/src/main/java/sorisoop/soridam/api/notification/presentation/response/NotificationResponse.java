package sorisoop.soridam.api.notification.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.domain.notification.domain.enums.NotificationType;

@Builder
public record NotificationResponse(
	@Schema(description = "알림 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "알림 대상 장소 ID", example = "42", requiredMode = REQUIRED)
	Long placeId,

	@Schema(description = "알림 내용", example = "즐겨찾기한 장소 서울숲에 새로운 리뷰가 등록되었습니다.", requiredMode = REQUIRED)
	String content,

	@Schema(description = "알림 타입", example = "REVIEW", requiredMode = REQUIRED)
	NotificationType notificationType,

	@Schema(description = "읽음 여부", example = "false", requiredMode = REQUIRED)
	boolean isRead,

	@Schema(description = "알림 생성일", example = "2025-05-12T21:37:00", requiredMode = REQUIRED)
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
