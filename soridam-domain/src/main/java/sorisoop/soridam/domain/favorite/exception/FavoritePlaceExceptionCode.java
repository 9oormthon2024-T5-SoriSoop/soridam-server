package sorisoop.soridam.domain.favorite.exception;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum FavoritePlaceExceptionCode implements ExceptionCode {
	FAVORITE_PLACE_NOT_FOUND(NOT_FOUND, "즐겨찾기 내역을 찾을 수 없습니다."),
	ALREADY_EXIST_FAVORITE_PLACE(CONFLICT, "해당 장소는 이미 즐겨찾기 되어 있습니다.");
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
