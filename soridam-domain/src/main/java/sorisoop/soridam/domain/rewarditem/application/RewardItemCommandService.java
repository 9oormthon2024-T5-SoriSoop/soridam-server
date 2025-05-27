package sorisoop.soridam.domain.rewarditem.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.rewarditem.domain.RewardItem;
import sorisoop.soridam.domain.rewarditem.domain.RewardItemType;
import sorisoop.soridam.domain.rewarditem.repository.RewardItemRepository;

@Service
@RequiredArgsConstructor
public class RewardItemCommandService {
	private final RewardItemRepository rewardItemRepository;

	public RewardItem createGood(String name, RewardItemType rewardItemType, String description, int pointCost, String imageUrl, Integer stock) {
		RewardItem rewardItem = RewardItem.create(name, rewardItemType, description, pointCost, imageUrl, stock);
		return rewardItemRepository.save(rewardItem);
	}

	public void hideGood(RewardItem rewardItem) {
		rewardItem.hide();
	}

	public void showGood(RewardItem rewardItem) {
		rewardItem.show();
	}
}
