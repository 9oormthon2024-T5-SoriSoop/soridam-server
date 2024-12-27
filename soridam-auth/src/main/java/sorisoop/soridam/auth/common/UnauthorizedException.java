package sorisoop.soridam.auth.common;

import static sorisoop.soridam.auth.common.AccessExceptionCode.UNAUTHORIZED;

import sorisoop.soridam.common.exception.CustomException;

public class UnauthorizedException extends CustomException {
	public UnauthorizedException() {
		super(UNAUTHORIZED);
	}
}
