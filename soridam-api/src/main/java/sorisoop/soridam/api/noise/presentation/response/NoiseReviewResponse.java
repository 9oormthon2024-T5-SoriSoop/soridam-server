package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.api.review.presentation.response.ReviewResponse;

@Builder
public record NoiseReviewResponse(
	@Schema(description = "소음 데이터 목록", requiredMode = REQUIRED)
	List<NoiseResponse> noises,

	@Schema(description = "소음 리뷰 데이터 목록", requiredMode = REQUIRED)
	List<ReviewResponse> reviews

) {
	public static NoiseReviewResponse of(List<NoiseResponse> noises, List<ReviewResponse> reviews) {
		return builder()
			.noises(noises)
			.reviews(reviews)
			.build();
	}
}
