package sorisoop.soridam.domain.place_service.place.exception;

import static sorisoop.soridam.domain.place_service.place.exception.PlaceExceptionCode.PLACE_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class PlaceNotFoundException extends CustomException {
	public PlaceNotFoundException() {
		super(PLACE_NOT_FOUND);
	}
}
