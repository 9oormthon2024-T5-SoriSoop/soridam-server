package sorisoop.soridam.api.reward.presentation;

import lombok.Builder;
import sorisoop.soridam.domain.reward.domain.PointRedemption;

@Builder
public record PointRedemptionPersistResponse(
	Long id
) {
	public static PointRedemptionPersistResponse from(PointRedemption pointRedemption) {
		return PointRedemptionPersistResponse.builder()
			.id(pointRedemption.getId())
			.build();
	}
}
