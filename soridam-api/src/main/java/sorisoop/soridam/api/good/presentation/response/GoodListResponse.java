package sorisoop.soridam.api.good.presentation.response;

import java.util.List;

import lombok.Builder;
import sorisoop.soridam.domain.rewarditem.domain.RewardItem;

@Builder
public record GoodListResponse(
	List<GoodResponse> response
) {
	public static GoodListResponse from(List<RewardItem> response) {
		return GoodListResponse.builder()
			.response(response.stream()
				.map(GoodResponse::from)
				.toList())
			.build();
	}
}
