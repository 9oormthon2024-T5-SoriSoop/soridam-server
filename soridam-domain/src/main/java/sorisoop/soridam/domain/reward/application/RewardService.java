package sorisoop.soridam.domain.reward.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward.domain.PointRedemption;
import sorisoop.soridam.domain.reward.exception.InsufficientPointException;
import sorisoop.soridam.domain.reward.repository.PointRedemptionRepository;
import sorisoop.soridam.domain.rewarditem.domain.Good;
import sorisoop.soridam.domain.user.user.domain.User;

@Service
@RequiredArgsConstructor
public class RewardService {
	private final PointRedemptionRepository pointRedemptionRepository;

	public PointRedemption requestRedemption(User user, Good good) {
		PointRedemption pointRedemption = PointRedemption.create(user, good);

		if (user.getTotalPoint() < good.getPointCost()) {
			throw new InsufficientPointException();
		}

		return pointRedemptionRepository.save(pointRedemption);
	}
}
