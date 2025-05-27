package sorisoop.soridam.api.good.presentation.response;

import lombok.Builder;
import sorisoop.soridam.domain.rewarditem.domain.RewardItem;

@Builder
public record GoodResponse(
	Long id,
	String name,
	String description,
	int pointCost,
	String imageUrl,
	Integer stock,
	boolean isHidden
) {
	public static GoodResponse from(RewardItem rewardItem) {
		return GoodResponse.builder()
			.id(rewardItem.getId())
			.name(rewardItem.getName())
			.description(rewardItem.getDescription())
			.pointCost(rewardItem.getPointCost())
			.imageUrl(rewardItem.getImageUrl())
			.stock(rewardItem.getStock())
			.isHidden(rewardItem.isHidden())
			.build();
	}
}
