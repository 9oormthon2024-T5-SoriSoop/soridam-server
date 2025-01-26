package sorisoop.soridam.api.review.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ReviewUpdateRequest(
	@Schema(description = "수정한 작성 내용", example = "content", requiredMode = REQUIRED)
	String content,

	@Schema(description = "평점", example = "5.0", requiredMode = REQUIRED)
	@NotNull
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "5.0")
	BigDecimal rating
) {
}
