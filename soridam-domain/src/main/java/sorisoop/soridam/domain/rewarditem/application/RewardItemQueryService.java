package sorisoop.soridam.domain.rewarditem.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.rewarditem.domain.RewardItem;
import sorisoop.soridam.domain.rewarditem.exception.RewardItemNotFoundException;
import sorisoop.soridam.domain.rewarditem.exception.RewardItemOutOfStockException;
import sorisoop.soridam.domain.rewarditem.repository.RewardItemRepository;

@Service
@RequiredArgsConstructor
public class RewardItemQueryService {
	private final RewardItemRepository rewardItemRepository;

	public List<RewardItem> getGoods() {
		return rewardItemRepository.findByHiddenFalse();
	}

	public RewardItem getGoodById(Long id) {
		RewardItem rewardItem = rewardItemRepository.findById(id)
			.orElseThrow(RewardItemNotFoundException::new);

		if (rewardItem.getStock() <= 0) throw new RewardItemOutOfStockException();

		return rewardItem;
	}
}
