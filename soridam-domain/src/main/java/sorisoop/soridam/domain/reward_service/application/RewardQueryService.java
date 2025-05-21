package sorisoop.soridam.domain.reward_service.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward_service.domain.Good;
import sorisoop.soridam.domain.reward_service.repository.GoodRepository;

@Service
@RequiredArgsConstructor
public class RewardQueryService {
	private final GoodRepository goodRepository;

	public List<Good> getGoods() {
		return goodRepository.findByHiddenFalse();
	}

	public Good getGoodById(Long id) {
		return goodRepository.findById(id).orElseThrow();
	}
}
