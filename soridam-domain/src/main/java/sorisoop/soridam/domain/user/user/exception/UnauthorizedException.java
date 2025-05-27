package sorisoop.soridam.domain.user.user.exception;

import static sorisoop.soridam.domain.user.user.exception.UserDomainExceptionCode.UNAUTHORIZED;

import sorisoop.soridam.common.exception.CustomException;

public class UnauthorizedException extends CustomException {
	public UnauthorizedException() {
		super(UNAUTHORIZED);
	}
}
