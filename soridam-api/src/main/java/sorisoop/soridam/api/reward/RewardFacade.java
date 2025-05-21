package sorisoop.soridam.api.reward;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.reward.presentation.request.GoodCreateRequest;
import sorisoop.soridam.api.reward.presentation.response.GoodListResponse;
import sorisoop.soridam.api.reward.presentation.response.GoodPersistResponse;
import sorisoop.soridam.domain.reward_service.application.RewardCommandService;
import sorisoop.soridam.domain.reward_service.application.RewardQueryService;
import sorisoop.soridam.domain.reward_service.domain.Good;

@Component
@RequiredArgsConstructor
public class RewardFacade {
	private final RewardCommandService rewardCommandService;
	private final RewardQueryService rewardQueryService;

	@Transactional
	public GoodPersistResponse createGood(GoodCreateRequest request) {
		Good good = rewardCommandService.createGood(
			request.name(),
			request.description(),
			request.pointCost(),
			request.imageUrl(),
			request.stock()
		);

		return GoodPersistResponse.from(good);
	}

	@Transactional
	public void hideGood(Long goodId) {
		Good good = rewardQueryService.getGoodById(goodId);
		rewardCommandService.hideGood(good);
	}

	@Transactional
	public void showGood(Long goodId) {
		Good good = rewardQueryService.getGoodById(goodId);
		rewardCommandService.showGood(good);
	}

	@Transactional
	public GoodListResponse getAllGoods() {
		List<Good> goods = rewardQueryService.getGoods();
		return GoodListResponse.from(goods);
	}
}
