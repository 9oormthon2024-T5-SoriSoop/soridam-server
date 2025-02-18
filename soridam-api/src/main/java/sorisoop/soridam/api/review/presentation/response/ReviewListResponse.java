package sorisoop.soridam.api.review.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ReviewListResponse(
	@Schema(description = "소음 데이터 목록", requiredMode = REQUIRED)
	List<ReviewResponse> reviews
) {
	public static ReviewListResponse of(List<ReviewResponse> reviews) {
		return builder()
			.reviews(reviews)
			.build();
	}
}
