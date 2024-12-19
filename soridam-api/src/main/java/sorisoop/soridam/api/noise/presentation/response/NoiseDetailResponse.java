package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseDetailResponse(
	@Schema(description = "소음 데이터 목록", requiredMode = REQUIRED)
	List<NoiseResponse> noises,

	@Schema(description = "소음 리뷰 데이터 목록", requiredMode = REQUIRED)
	List<NoiseReviewResponse> reviews

) {
	public static NoiseDetailResponse of(List<NoiseResponse> noises, List<NoiseReviewResponse> reviews) {
		return builder()
			.noises(noises)
			.reviews(reviews)
			.build();
	}
}
