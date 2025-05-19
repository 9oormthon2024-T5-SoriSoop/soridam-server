package sorisoop.soridam.domain.notification.domain.dto;

public record ReviewNotificationTarget(
	Long userId,
	Long placeId,
	String placeName
) {}
