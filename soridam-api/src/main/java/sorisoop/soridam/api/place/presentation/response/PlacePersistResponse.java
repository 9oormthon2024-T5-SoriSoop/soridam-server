package sorisoop.soridam.api.place.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.place.place.domain.Place;

@Builder
public record PlacePersistResponse(
	@Schema(description = "장소 ID", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static PlacePersistResponse from(Place place) {
		return PlacePersistResponse.builder()
			.id(place.getId())
			.build();
	}
}
