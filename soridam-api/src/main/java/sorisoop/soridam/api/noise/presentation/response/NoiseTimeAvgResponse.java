package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.LocalDateTime;

import sorisoop.soridam.domain.noise.domain.Noise;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseTimeAvgResponse(
	@Schema(description = "평균 데시벨", example = "50", requiredMode = REQUIRED)
	int avgDecibel,

	@Schema(description = "생성 시간", example = "2024-12-14T21:37:00", requiredMode = REQUIRED)
	LocalDateTime createdAt
) {
	public static NoiseTimeAvgResponse from(Noise noise) {
		return builder()
			.avgDecibel(noise.getAvgDecibel())
			.createdAt(noise.getCreatedAt())
			.build();
	}
}
