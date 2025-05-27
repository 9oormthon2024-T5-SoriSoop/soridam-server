package sorisoop.soridam.domain.place.place.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum PlaceExceptionCode implements ExceptionCode {
	PLACE_NOT_FOUND(NOT_FOUND, "해당 장소를 찾을 수 없습니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
