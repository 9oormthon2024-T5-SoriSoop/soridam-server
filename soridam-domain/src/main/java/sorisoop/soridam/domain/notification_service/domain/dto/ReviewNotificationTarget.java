package sorisoop.soridam.domain.notification_service.domain.dto;

public record ReviewNotificationTarget(
	Long userId,
	Long placeId,
	String placeName
) {}
