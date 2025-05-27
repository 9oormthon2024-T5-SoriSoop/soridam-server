package sorisoop.soridam.api.reward.application;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward.application.RewardService;
import sorisoop.soridam.domain.reward.domain.PointRedemption;
import sorisoop.soridam.domain.rewarditem.application.RewardItemQueryService;
import sorisoop.soridam.domain.rewarditem.domain.Good;
import sorisoop.soridam.domain.user.user.application.UserQueryService;
import sorisoop.soridam.domain.user.user.domain.User;
import sorisoop.soridam.infra.reward.RedemptionRequestedEvent;
import sorisoop.soridam.infra.reward.RewardAsyncService;

@Component
@RequiredArgsConstructor
public class RewardFacade {
	private final RewardService rewardService;
	private final UserQueryService userQueryService;
	private final RewardItemQueryService rewardItemQueryService;
	private final RewardAsyncService rewardAsyncService;

	public PointRedemption requestReward(Long goodId) {
		User user = userQueryService.me();
		Good good = rewardItemQueryService.getGoodById(goodId);

		PointRedemption pointRedemption = rewardService.requestRedemption(user, good);
		RedemptionRequestedEvent event = RedemptionRequestedEvent.of(pointRedemption.getId(), user.getId());
		rewardAsyncService.handleRedemptionRequest(event);
		return pointRedemption;
	}
}
