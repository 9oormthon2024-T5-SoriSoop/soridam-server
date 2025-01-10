package sorisoop.soridam.auth.common;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum AccessExceptionCode implements ExceptionCode {
	ACCESS_DENIED(FORBIDDEN, "권한이 없습니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
