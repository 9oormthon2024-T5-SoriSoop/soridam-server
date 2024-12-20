package sorisoop.soridam.auth.jwt.exception;

import static sorisoop.soridam.auth.jwt.exception.JwtExceptionCode.JWT_TOKEN_INVALID;

import sorisoop.soridam.common.exception.CustomException;

public class JwtInvalidException extends CustomException {
	public JwtInvalidException() {
		super(JWT_TOKEN_INVALID);
	}
}
