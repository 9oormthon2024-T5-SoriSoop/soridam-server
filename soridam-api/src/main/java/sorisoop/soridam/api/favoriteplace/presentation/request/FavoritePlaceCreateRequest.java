package sorisoop.soridam.api.favoriteplace.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FavoritePlaceCreateRequest(
	@Schema(description = "장소 ID", example = "1", requiredMode = REQUIRED)
	@NotNull
	Long placeId
) {
}
