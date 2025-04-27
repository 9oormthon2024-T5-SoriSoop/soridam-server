package sorisoop.soridam.api.review.presentation.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import sorisoop.soridam.domain.review.domain.Review;

@Builder
public record ReviewResponse(
	Long targetId,
	String authorId,
	String content,
	BigDecimal rating,
	LocalDateTime createdAt
) {
	public static ReviewResponse from(Review review) {
		return ReviewResponse.builder()
			.authorId(review.getAuthor().getNickname())
			.targetId(review.getTargetId())
			.content(review.getContent())
			.rating(review.getRating())
			.createdAt(review.getCreatedAt())
			.build();
	}
}
