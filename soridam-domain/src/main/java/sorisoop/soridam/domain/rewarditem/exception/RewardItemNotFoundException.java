package sorisoop.soridam.domain.rewarditem.exception;

import static sorisoop.soridam.domain.rewarditem.exception.RewardItemExceptionCode.REWARD_ITEM_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class RewardItemNotFoundException extends CustomException {
	public RewardItemNotFoundException() {
		super(REWARD_ITEM_NOT_FOUND);
	}
}
