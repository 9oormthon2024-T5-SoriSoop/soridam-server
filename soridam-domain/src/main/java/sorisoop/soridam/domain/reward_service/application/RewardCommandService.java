package sorisoop.soridam.domain.reward_service.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward_service.domain.Good;
import sorisoop.soridam.domain.reward_service.repository.GoodRepository;

@Service
@RequiredArgsConstructor
public class RewardCommandService {
	private final GoodRepository goodRepository;

	public Good createGood(String name, String description, int pointCost, String imageUrl, Integer stock) {
		Good good = Good.create(name, description, pointCost, imageUrl, stock);
		return goodRepository.save(good);
	}
}
