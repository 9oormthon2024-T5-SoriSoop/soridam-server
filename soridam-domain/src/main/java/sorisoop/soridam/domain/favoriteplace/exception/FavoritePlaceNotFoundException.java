package sorisoop.soridam.domain.favoriteplace.exception;

import static sorisoop.soridam.domain.favoriteplace.exception.FavoritePlaceExceptionCode.FAVORITE_PLACE_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class FavoritePlaceNotFoundException extends CustomException {
	public FavoritePlaceNotFoundException() {
		super(FAVORITE_PLACE_NOT_FOUND);
	}
}
