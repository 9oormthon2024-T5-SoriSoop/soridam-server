package sorisoop.soridam.auth.jwt.exception;

import static sorisoop.soridam.auth.jwt.exception.JwtExceptionCode.JWT_SIGNATURE_INVALID;

import sorisoop.soridam.common.exception.CustomException;

public class JwtSignatureInvalidException extends CustomException {
	public JwtSignatureInvalidException() {
		super(JWT_SIGNATURE_INVALID);
	}
}
