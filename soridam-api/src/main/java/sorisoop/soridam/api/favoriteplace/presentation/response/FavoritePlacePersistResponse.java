package sorisoop.soridam.api.favoriteplace.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.favorite_service.domain.FavoritePlace;

@Builder
public record FavoritePlacePersistResponse(
	@Schema(description = "장소 즐겨찾기 ID", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static FavoritePlacePersistResponse from(FavoritePlace favoritePlace) {
		return FavoritePlacePersistResponse.builder()
			.id(favoritePlace.getId())
			.build();
	}
}
