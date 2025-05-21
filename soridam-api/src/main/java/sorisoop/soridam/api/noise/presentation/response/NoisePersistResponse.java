package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.place_service.noise.domain.Noise;

@Builder
public record NoisePersistResponse(
	@Schema(description = "noise ID", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static NoisePersistResponse from(Noise noise){
		return NoisePersistResponse.builder()
			.id(noise.getId())
			.build();
	}
}
