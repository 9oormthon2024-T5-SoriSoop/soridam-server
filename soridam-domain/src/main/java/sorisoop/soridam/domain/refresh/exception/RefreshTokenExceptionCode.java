package sorisoop.soridam.domain.refresh.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum RefreshTokenExceptionCode implements ExceptionCode {
	REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "해당 토큰을 찾을 수 없습니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
