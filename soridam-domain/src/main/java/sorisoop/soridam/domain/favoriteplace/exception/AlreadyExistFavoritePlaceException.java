package sorisoop.soridam.domain.favoriteplace.exception;

import static sorisoop.soridam.domain.favoriteplace.exception.FavoritePlaceExceptionCode.ALREADY_EXIST_FAVORITE_PLACE;

import sorisoop.soridam.common.exception.CustomException;

public class AlreadyExistFavoritePlaceException extends CustomException {
	public AlreadyExistFavoritePlaceException() {
		super(ALREADY_EXIST_FAVORITE_PLACE);
	}
}
