package sorisoop.soridam.auth.common;

import static sorisoop.soridam.auth.common.AccessExceptionCode.ACCESS_DENIED;

import sorisoop.soridam.common.exception.CustomException;

public class CustomAccessDeniedException extends CustomException {
	public CustomAccessDeniedException() {
		super(ACCESS_DENIED);
	}
}
