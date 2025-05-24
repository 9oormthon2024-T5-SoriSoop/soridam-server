package sorisoop.soridam.domain.rewarditem_service.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.rewarditem_service.domain.Good;
import sorisoop.soridam.domain.rewarditem_service.repository.GoodRepository;

@Service
@RequiredArgsConstructor
public class RewardItemQueryService {
	private final GoodRepository goodRepository;

	public List<Good> getGoods() {
		return goodRepository.findByHiddenFalse();
	}

	public Good getGoodById(Long id) {
		return goodRepository.findById(id).orElseThrow();
	}
}
