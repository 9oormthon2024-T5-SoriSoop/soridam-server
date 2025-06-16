package sorisoop.soridam.domain.user.user.exception;

import static sorisoop.soridam.domain.user.user.exception.UserDomainExceptionCode.INVALID_USER;

import sorisoop.soridam.common.exception.CustomException;

public class InvalidUserException extends CustomException {
	public InvalidUserException() {
		super(INVALID_USER);
	}
}
