package sorisoop.soridam.domain.rewarditem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import sorisoop.soridam.domain.rewarditem.domain.RewardItem;

@Repository
public interface RewardItemRepository {
	RewardItem save(RewardItem rewardItem);

	List<RewardItem> findByHiddenFalse();

	Optional<RewardItem> findById(Long id);
}
