package sorisoop.soridam.infra.config.data.redis.event;

import java.time.LocalDateTime;

public record ReviewCreatedEvent(
	Long placeId,
	Long writerId,
	String reviewContent,
	LocalDateTime createdAt
) {
}
