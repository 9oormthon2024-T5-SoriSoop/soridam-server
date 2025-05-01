package sorisoop.soridam.domain.place.exception;

import static sorisoop.soridam.domain.place.exception.AddressExceptionCode.ADDRESS_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class AddressNotFoundException extends CustomException {
	public AddressNotFoundException() {
		super(ADDRESS_NOT_FOUND);
	}
}
