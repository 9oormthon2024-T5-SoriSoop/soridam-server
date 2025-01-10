package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.noise.domain.Noise;

@Builder
public record NoisePersistResponse(
	@Schema(description = "noise ID", example = "nse_asdfjklsadjklsamlsdfsldm", requiredMode = REQUIRED)
	String id
) {
	public static NoisePersistResponse from(Noise noise){
		return builder()
			.id(noise.extractUuid())
			.build();
	}
}
