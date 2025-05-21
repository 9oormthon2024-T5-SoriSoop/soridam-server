package sorisoop.soridam.api.review.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sorisoop.soridam.domain.review_service.domain.ReviewType;

@Builder
public record ReviewCreateRequest(
	@Schema(description = "게시글 ID", example = "1", requiredMode = REQUIRED)
	@NotNull
	Long targetId,

	@Schema(description = "게시글 타입", example = "NOISE", requiredMode = REQUIRED)
	@NotNull
	ReviewType reviewType,

	@Schema(description = "작성 내용", example = "content", requiredMode = REQUIRED)
	String content,

	@Schema(description = "평점", example = "5.0", requiredMode = REQUIRED)
	@NotNull
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "5.0")
	BigDecimal rating
) {
}
