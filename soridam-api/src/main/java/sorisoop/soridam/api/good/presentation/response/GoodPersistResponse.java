package sorisoop.soridam.api.good.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.rewarditem.domain.Good;

@Builder
public record GoodPersistResponse(
	@Schema(description = "보상 ID", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static GoodPersistResponse from(Good good) {
		return GoodPersistResponse.builder()
			.id(good.getId())
			.build();
	}
}
