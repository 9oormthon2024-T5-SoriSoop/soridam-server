package sorisoop.soridam.api.user.presentation.exception;

import static sorisoop.soridam.api.user.presentation.exception.UserExceptionCode.USER_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class UserNotFoundException extends CustomException {
	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}
}
