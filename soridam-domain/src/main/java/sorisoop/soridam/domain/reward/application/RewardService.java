package sorisoop.soridam.domain.reward.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward.domain.PointRedemption;
import sorisoop.soridam.domain.reward.exception.InsufficientPointException;
import sorisoop.soridam.domain.reward.repository.PointRedemptionRepository;
import sorisoop.soridam.domain.rewarditem.domain.RewardItem;
import sorisoop.soridam.domain.user.user.domain.User;

@Service
@RequiredArgsConstructor
public class RewardService {
	private final PointRedemptionRepository pointRedemptionRepository;

	public PointRedemption requestRedemption(User user, RewardItem rewardItem) {
		PointRedemption pointRedemption = PointRedemption.create(user, rewardItem);

		if (user.getTotalPoint() < rewardItem.getPointCost()) {
			throw new InsufficientPointException();
		}

		return pointRedemptionRepository.save(pointRedemption);
	}
}
