package sorisoop.soridam.domain.user.user.exception;

import static sorisoop.soridam.domain.user.user.exception.UserDomainExceptionCode.USER_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class UserNotFoundException extends CustomException {
	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}
}
