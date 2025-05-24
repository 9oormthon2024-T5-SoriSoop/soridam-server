package sorisoop.soridam.api.favoriteplace.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sorisoop.soridam.domain.favorite_service.domain.FavoritePlace;
import sorisoop.soridam.domain.place_service.place.domain.Place;
import sorisoop.soridam.domain.place_service.place.domain.enums.Category;

@Builder
public record FavoritePlaceResponse(
	@Schema(description = "즐겨찾기 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "장소 ID", example = "1", requiredMode = REQUIRED)
	Long placeId,

	@Schema(description = "도로명 주소", example = "서울특별시 동대문구 장한로 110 (장안동)", requiredMode = REQUIRED)
	@NotNull
	String roadAddress,

	@Schema(description = "장소명, 업체명", example = "경기대학교")
	String placeName,

	@Schema(description = "장소 카테고리", example = "MT1", requiredMode = REQUIRED)
	Category category
) {
	public static FavoritePlaceResponse from(FavoritePlace favoritePlace) {
		Place place = favoritePlace.getPlace();

		return FavoritePlaceResponse.builder()
			.id(favoritePlace.getId())
			.placeId(place.getId())
			.roadAddress(place.getRoadAddress())
			.placeName(place.getPlaceName())
			.category(place.getCategory())
			.build();
	}
}
