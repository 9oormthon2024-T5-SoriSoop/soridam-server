package sorisoop.soridam.infra.reward;

import lombok.Builder;

@Builder
public record RedemptionRequestedEvent(
	Long redemptionId,
	Long userId
) {
	public static RedemptionRequestedEvent of(Long redemptionId, Long userId) {
		return RedemptionRequestedEvent.builder()
			.redemptionId(redemptionId)
			.userId(userId)
			.build();
	}
}
