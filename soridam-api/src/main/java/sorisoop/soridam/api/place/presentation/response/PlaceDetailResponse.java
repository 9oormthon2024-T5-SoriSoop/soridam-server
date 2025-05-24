package sorisoop.soridam.api.place.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sorisoop.soridam.domain.place_service.place.domain.Place;
import sorisoop.soridam.domain.place_service.place.domain.enums.Category;

@Builder
public record PlaceDetailResponse(
	@Schema(description = "장소 Idx", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "X 좌표 (경도)", example = "126.9780", requiredMode = REQUIRED)
	double x,

	@Schema(description = "Y 좌표 (위도)", example = "37.5665", requiredMode = REQUIRED)
	double y,

	@Schema(description = "도로명 주소", example = "서울특별시 동대문구 장한로 110 (장안동)", requiredMode = REQUIRED)
	@NotNull
	String roadAddress,

	@Schema(description = "지번 주소", example = "서울특별시 동대문구 장안동 366-7", requiredMode = REQUIRED)
	@NotNull
	String regionAddress,

	@Schema(description = "장소 카테고리", example = "MT1", requiredMode = REQUIRED)
	Category category,

	@Schema(description = "장소명, 업체명", example = "경기대학교")
	String placeName,

	@Schema(description = "장소 상세페이지 url", example = "경기대학교")
	String placeUrl,

	@Schema(description = "장소에 대한 리뷰 요약", example = "조용해서 공부하기 좋습니다.")
	String summary
) {
	public static PlaceDetailResponse of(Place place, String summary) {
		return PlaceDetailResponse.builder()
			.id(place.getId())
			.x(place.getLocation().getX())
			.y(place.getLocation().getY())
			.roadAddress(place.getRoadAddress())
			.regionAddress(place.getRegionAddress())
			.category(place.getCategory())
			.placeName(place.getPlaceName())
			.placeUrl(place.getPlaceUrl())
			.summary(summary)
			.build();
	}
}
