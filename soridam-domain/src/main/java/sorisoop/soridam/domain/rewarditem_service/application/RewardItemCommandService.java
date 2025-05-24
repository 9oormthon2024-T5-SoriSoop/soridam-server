package sorisoop.soridam.domain.rewarditem_service.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.rewarditem_service.domain.Good;
import sorisoop.soridam.domain.rewarditem_service.domain.GoodType;
import sorisoop.soridam.domain.rewarditem_service.repository.GoodRepository;

@Service
@RequiredArgsConstructor
public class RewardItemCommandService {
	private final GoodRepository goodRepository;

	public Good createGood(String name, GoodType goodType, String description, int pointCost, String imageUrl, Integer stock) {
		Good good = Good.create(name, goodType, description, pointCost, imageUrl, stock);
		return goodRepository.save(good);
	}

	public void hideGood(Good good) {
		good.hide();
	}

	public void showGood(Good good) {
		good.show();
	}
}
