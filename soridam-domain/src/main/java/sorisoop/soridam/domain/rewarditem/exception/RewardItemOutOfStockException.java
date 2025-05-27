package sorisoop.soridam.domain.rewarditem.exception;

import static sorisoop.soridam.domain.rewarditem.exception.RewardItemExceptionCode.REWARD_ITEM_OUT_OF_STOCK;

import sorisoop.soridam.common.exception.CustomException;

public class RewardItemOutOfStockException extends CustomException {
	public RewardItemOutOfStockException() {
		super(REWARD_ITEM_OUT_OF_STOCK);
	}
}
