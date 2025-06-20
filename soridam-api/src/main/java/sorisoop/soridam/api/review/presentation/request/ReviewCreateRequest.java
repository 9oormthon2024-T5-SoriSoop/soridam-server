package sorisoop.soridam.api.review.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.math.BigDecimal;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sorisoop.soridam.domain.review.domain.ReviewTag;

@Builder
public record ReviewCreateRequest(

	@Schema(description = "리뷰 대상 장소 ID", example = "1", requiredMode = REQUIRED)
	@NotNull
	Long placeId,

	@Schema(description = "리뷰 태그 목록", example = "[\"QUIET\", \"CLEAN\"]", requiredMode = REQUIRED)
	@NotNull
	Set<ReviewTag> tags,

	@Schema(description = "리뷰 내용", example = "조용하고 깔끔해요!", requiredMode = REQUIRED)
	String content,

	@Schema(description = "평점 (0.0 ~ 5.0)", example = "4.5", requiredMode = REQUIRED)
	@NotNull
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "5.0")
	BigDecimal rating
) {}

