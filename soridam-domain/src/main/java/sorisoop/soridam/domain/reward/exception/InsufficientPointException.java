package sorisoop.soridam.domain.reward.exception;

import static sorisoop.soridam.domain.reward.exception.PointRedemptionExceptionCode.INSUFFICIENT_POINT;

import sorisoop.soridam.common.exception.CustomException;

public class InsufficientPointException extends CustomException {
	public InsufficientPointException() {
		super(INSUFFICIENT_POINT);
	}
}
