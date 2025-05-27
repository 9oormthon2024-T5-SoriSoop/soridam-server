package sorisoop.soridam.infra.notification;

import java.time.LocalDateTime;

import lombok.Builder;
import sorisoop.soridam.domain.review.domain.Review;

@Builder
public record ReviewCreatedEvent(
	Long reviewId,
	Long placeId,
	Long writerId,
	String reviewContent,
	LocalDateTime createdAt
) {
	public static ReviewCreatedEvent from(Review review) {
		return ReviewCreatedEvent.builder()
			.reviewId(review.getId())
			.placeId(review.getTargetId())
			.writerId(review.getAuthor().getId())
			.createdAt(review.getCreatedAt())
			.build();
	}
}
