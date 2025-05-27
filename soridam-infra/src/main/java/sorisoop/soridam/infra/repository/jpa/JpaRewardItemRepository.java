package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.rewarditem.domain.RewardItem;

public interface JpaRewardItemRepository extends JpaRepository<RewardItem, Long> {
	List<RewardItem> findByHiddenFalse();
}
