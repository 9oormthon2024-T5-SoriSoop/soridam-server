package sorisoop.soridam.domain.user_service.user.exception;

import static sorisoop.soridam.domain.user_service.user.exception.UserDomainExceptionCode.INVALID_PASSWORD;

import sorisoop.soridam.common.exception.CustomException;

public class InvalidPasswordException extends CustomException {
	public InvalidPasswordException() {
		super(INVALID_PASSWORD);
	}
}
