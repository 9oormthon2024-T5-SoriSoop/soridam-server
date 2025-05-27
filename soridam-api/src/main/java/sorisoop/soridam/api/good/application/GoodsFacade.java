package sorisoop.soridam.api.good.application;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.good.presentation.request.GoodCreateRequest;
import sorisoop.soridam.api.good.presentation.response.GoodListResponse;
import sorisoop.soridam.api.good.presentation.response.GoodPersistResponse;
import sorisoop.soridam.domain.rewarditem.application.RewardItemCommandService;
import sorisoop.soridam.domain.rewarditem.application.RewardItemQueryService;
import sorisoop.soridam.domain.rewarditem.domain.RewardItem;

@Component
@RequiredArgsConstructor
public class GoodsFacade {
	private final RewardItemCommandService rewardCommandService;
	private final RewardItemQueryService rewardItemQueryService;

	@Transactional
	public GoodPersistResponse createGood(GoodCreateRequest request) {
		RewardItem rewardItem = rewardCommandService.createGood(
			request.name(),
			request.rewardItemType(),
			request.description(),
			request.pointCost(),
			request.imageUrl(),
			request.stock()
		);

		return GoodPersistResponse.from(rewardItem);
	}

	@Transactional
	public void hideGood(Long goodId) {
		RewardItem rewardItem = rewardItemQueryService.getGoodById(goodId);
		rewardCommandService.hideGood(rewardItem);
	}

	@Transactional
	public void showGood(Long goodId) {
		RewardItem rewardItem = rewardItemQueryService.getGoodById(goodId);
		rewardCommandService.showGood(rewardItem);
	}

	@Transactional
	public GoodListResponse getAllGoods() {
		List<RewardItem> rewardItems = rewardItemQueryService.getGoods();
		return GoodListResponse.from(rewardItems);
	}
}
