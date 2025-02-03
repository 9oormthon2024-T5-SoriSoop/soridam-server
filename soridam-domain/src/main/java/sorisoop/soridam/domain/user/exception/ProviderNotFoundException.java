package sorisoop.soridam.domain.user.exception;

import static sorisoop.soridam.common.exception.GlobalExceptionCode.PROVIDER_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class ProviderNotFoundException extends CustomException {
	public ProviderNotFoundException() {
		super(PROVIDER_NOT_FOUND);
	}
}
