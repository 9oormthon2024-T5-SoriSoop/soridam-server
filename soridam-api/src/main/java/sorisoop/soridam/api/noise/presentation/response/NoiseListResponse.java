package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseListResponse(
	@Schema(description = "소음 데이터 목록", requiredMode = REQUIRED)
	List<NoiseResponse> noises
) {
	public static NoiseListResponse of(List<NoiseResponse> noises) {
		return builder()
			.noises(noises)
			.build();
	}
}
