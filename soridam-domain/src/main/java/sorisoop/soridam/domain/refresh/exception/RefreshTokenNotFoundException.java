package sorisoop.soridam.domain.refresh.exception;

import static sorisoop.soridam.domain.refresh.exception.RefreshTokenExceptionCode.REFRESH_TOKEN_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class RefreshTokenNotFoundException extends CustomException {
	public RefreshTokenNotFoundException() {
		super(REFRESH_TOKEN_NOT_FOUND);
	}
}
