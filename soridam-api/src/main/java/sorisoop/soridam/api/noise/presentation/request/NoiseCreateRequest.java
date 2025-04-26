package sorisoop.soridam.api.noise.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record NoiseCreateRequest(
	@Schema(description = "장소 id", example = "address-adsfsdfasdaf", requiredMode = REQUIRED)
	@NotNull
	String addressId,

	@Schema(description = "평균 데시벨", example = "50", requiredMode = REQUIRED)
	@NotNull
	@Positive
	int avgDecibel,

	@Schema(description = "최대 데시벨", example = "70", requiredMode = REQUIRED)
	@NotNull
	@Positive
	int maxDecibel,

	@Schema(description = "리뷰 내용", example = "소음이 많은 환경", requiredMode = REQUIRED)
	@NotBlank
	String review
) {
}
