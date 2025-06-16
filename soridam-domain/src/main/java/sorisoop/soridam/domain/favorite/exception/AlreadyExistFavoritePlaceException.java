package sorisoop.soridam.domain.favorite.exception;

import sorisoop.soridam.common.exception.CustomException;

public class AlreadyExistFavoritePlaceException extends CustomException {
	public AlreadyExistFavoritePlaceException() {
		super(FavoritePlaceExceptionCode.ALREADY_EXIST_FAVORITE_PLACE);
	}
}
