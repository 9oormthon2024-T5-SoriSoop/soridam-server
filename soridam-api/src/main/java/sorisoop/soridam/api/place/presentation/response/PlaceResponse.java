package sorisoop.soridam.api.place.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sorisoop.soridam.domain.place.domain.Place;
import sorisoop.soridam.domain.place.domain.enums.Category;

@Builder
public record PlaceResponse(
	@Schema(description = "장소 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "X 좌표 (경도)", example = "126.9780", requiredMode = REQUIRED)
	double x,

	@Schema(description = "Y 좌표 (위도)", example = "37.5665", requiredMode = REQUIRED)
	double y,

	@Schema(description = "도로명 주소", example = "서울특별시 동대문구 장한로 110 (장안동)", requiredMode = REQUIRED)
	@NotNull
	String roadAddress,

	@Schema(description = "장소명, 업체명", example = "경기대학교")
	String placeName,

	@Schema(description = "장소 카테고리", example = "MT1", requiredMode = REQUIRED)
	Category category
) {
	public static PlaceResponse from(Place place) {
		return PlaceResponse.builder()
			.id(place.getId())
			.x(place.getLocation().getX())
			.y(place.getLocation().getY())
			.roadAddress(place.getRoadAddress())
			.placeName(place.getPlaceName())
			.category(place.getCategory())
			.build();
	}
}
