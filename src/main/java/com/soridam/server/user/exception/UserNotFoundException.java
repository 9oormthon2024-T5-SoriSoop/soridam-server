package com.soridam.server.user.exception;

import static com.soridam.server.user.exception.UserExceptionCode.USER_NOT_FOUND;

import com.soridam.server.common.exception.CustomException;

public class UserNotFoundException extends CustomException {
	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}
}
