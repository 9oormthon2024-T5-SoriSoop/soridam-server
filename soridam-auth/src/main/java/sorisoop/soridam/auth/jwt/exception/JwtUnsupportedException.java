package sorisoop.soridam.auth.jwt.exception;

import static sorisoop.soridam.auth.jwt.exception.JwtExceptionCode.JWT_TOKEN_UNSUPPORTED;

import sorisoop.soridam.common.exception.CustomException;

public class JwtUnsupportedException extends CustomException {
	public JwtUnsupportedException() {
		super(JWT_TOKEN_UNSUPPORTED);
	}
}
