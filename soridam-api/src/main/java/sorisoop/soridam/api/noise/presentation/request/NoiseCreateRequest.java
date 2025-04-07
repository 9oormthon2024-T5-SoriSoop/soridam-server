package sorisoop.soridam.api.noise.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import sorisoop.soridam.domain.address.domain.enums.Category;

@Builder
public record NoiseCreateRequest(
	@Schema(description = "X 좌표 (경도)", example = "126.9780", requiredMode = REQUIRED)
	@NotNull
	double x,

	@Schema(description = "Y 좌표 (위도)", example = "37.5665", requiredMode = REQUIRED)
	@NotNull
	double y,

	@Schema(description = "도로명 주소", example = "서울특별시 동대문구 장한로 110 (장안동)", requiredMode = REQUIRED)
	@NotNull
	String roadAddress,

	@Schema(description = "지번 주소", example = "서울특별시 동대문구 장안동 366-7", requiredMode = REQUIRED)
	@NotNull
	String regionAddress,

	@Schema(description = "장소 카테고리", example = "MT1", requiredMode = REQUIRED)
	@NotNull
	Category category,

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
