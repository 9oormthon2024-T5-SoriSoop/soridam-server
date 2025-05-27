package sorisoop.soridam.api.goods.application;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.goods.presentation.request.GoodCreateRequest;
import sorisoop.soridam.api.goods.presentation.response.GoodListResponse;
import sorisoop.soridam.api.goods.presentation.response.GoodPersistResponse;
import sorisoop.soridam.domain.rewarditem.application.RewardItemCommandService;
import sorisoop.soridam.domain.rewarditem.application.RewardItemQueryService;
import sorisoop.soridam.domain.rewarditem.domain.Good;

@Component
@RequiredArgsConstructor
public class GoodsFacade {
	private final RewardItemCommandService rewardCommandService;
	private final RewardItemQueryService rewardItemQueryService;

	@Transactional
	public GoodPersistResponse createGood(GoodCreateRequest request) {
		Good good = rewardCommandService.createGood(
			request.name(),
			request.goodType(),
			request.description(),
			request.pointCost(),
			request.imageUrl(),
			request.stock()
		);

		return GoodPersistResponse.from(good);
	}

	@Transactional
	public void hideGood(Long goodId) {
		Good good = rewardItemQueryService.getGoodById(goodId);
		rewardCommandService.hideGood(good);
	}

	@Transactional
	public void showGood(Long goodId) {
		Good good = rewardItemQueryService.getGoodById(goodId);
		rewardCommandService.showGood(good);
	}

	@Transactional
	public GoodListResponse getAllGoods() {
		List<Good> goods = rewardItemQueryService.getGoods();
		return GoodListResponse.from(goods);
	}
}
