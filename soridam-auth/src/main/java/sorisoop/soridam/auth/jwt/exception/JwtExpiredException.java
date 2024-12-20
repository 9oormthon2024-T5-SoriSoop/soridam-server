package sorisoop.soridam.auth.jwt.exception;

import static sorisoop.soridam.auth.jwt.exception.JwtExceptionCode.JWT_TOKEN_EXPIRED;

import sorisoop.soridam.common.exception.CustomException;

public class JwtExpiredException extends CustomException {
	public JwtExpiredException() {
		super(JWT_TOKEN_EXPIRED);
	}
}
