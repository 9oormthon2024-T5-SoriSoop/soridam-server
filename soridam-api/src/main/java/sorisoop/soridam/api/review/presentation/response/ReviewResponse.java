package sorisoop.soridam.api.review.presentation.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import sorisoop.soridam.domain.review.domain.Review;

@Builder
public record ReviewResponse(
	Long id,
	Long placeId,
	String authorId,
	String content,
	BigDecimal rating,
	LocalDateTime createdAt
) {
	public static ReviewResponse from(Review review) {
		return ReviewResponse.builder()
			.id(review.getId())
			.authorId(review.getAuthor().getNickname())
			.placeId(review.getPlace().getId())
			.content(review.getContent())
			.rating(review.getRating())
			.createdAt(review.getCreatedAt())
			.build();
	}
}
