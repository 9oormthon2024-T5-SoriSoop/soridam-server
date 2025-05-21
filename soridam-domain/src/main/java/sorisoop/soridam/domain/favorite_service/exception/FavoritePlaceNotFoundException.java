package sorisoop.soridam.domain.favorite_service.exception;

import static sorisoop.soridam.domain.favorite_service.exception.FavoritePlaceExceptionCode.FAVORITE_PLACE_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class FavoritePlaceNotFoundException extends CustomException {
	public FavoritePlaceNotFoundException() {
		super(FAVORITE_PLACE_NOT_FOUND);
	}
}
