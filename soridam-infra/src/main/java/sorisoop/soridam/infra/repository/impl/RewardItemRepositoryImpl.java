package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.rewarditem.domain.RewardItem;
import sorisoop.soridam.domain.rewarditem.repository.RewardItemRepository;
import sorisoop.soridam.infra.repository.jpa.JpaRewardItemRepository;

@Repository
@RequiredArgsConstructor
public class RewardItemRepositoryImpl implements RewardItemRepository {
	private final JpaRewardItemRepository jpaRewardItemRepository;

	@Override
	public RewardItem save(RewardItem rewardItem) {
		return jpaRewardItemRepository.save(rewardItem);
	}

	@Override
	public List<RewardItem> findByHiddenFalse() {
		return jpaRewardItemRepository.findByHiddenFalse();
	}

	@Override
	public Optional<RewardItem> findById(Long id) {
		return jpaRewardItemRepository.findById(id);
	}
}
