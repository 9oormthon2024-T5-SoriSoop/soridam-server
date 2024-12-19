package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseSearchResponse(
	@Schema(description = "소음의 시간, 평균 목록", requiredMode = REQUIRED)
	List<NoiseTimeAvgResponse> noises,

	@Schema(description = "소음 관련 리뷰 목록", requiredMode = REQUIRED)
	List<NoiseReviewResponse> reviews
) {
	public static NoiseSearchResponse of(List<NoiseTimeAvgResponse> noises, List<NoiseReviewResponse> reviews) {
		return builder()
			.noises(noises)
			.reviews(reviews)
			.build();
	}
}
