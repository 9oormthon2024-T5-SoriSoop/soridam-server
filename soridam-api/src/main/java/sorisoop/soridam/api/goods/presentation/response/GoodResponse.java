package sorisoop.soridam.api.goods.presentation.response;

import lombok.Builder;
import sorisoop.soridam.domain.rewarditem_service.domain.Good;

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
	public static GoodResponse from(Good good) {
		return GoodResponse.builder()
			.id(good.getId())
			.name(good.getName())
			.description(good.getDescription())
			.pointCost(good.getPointCost())
			.imageUrl(good.getImageUrl())
			.stock(good.getStock())
			.isHidden(good.isHidden())
			.build();
	}
}
