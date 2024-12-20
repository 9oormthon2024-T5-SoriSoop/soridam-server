package sorisoop.soridam.auth.jwt.exception;

import static sorisoop.soridam.auth.jwt.exception.JwtExceptionCode.JWT_TOKEN_MALFORMED;

import sorisoop.soridam.common.exception.CustomException;

public class JwtMalformedException extends CustomException {
	public JwtMalformedException() {
		super(JWT_TOKEN_MALFORMED);
	}
}
