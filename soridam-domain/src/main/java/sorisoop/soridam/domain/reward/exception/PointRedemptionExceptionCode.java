package sorisoop.soridam.domain.reward.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum PointRedemptionExceptionCode implements ExceptionCode {
	INSUFFICIENT_POINT(BAD_REQUEST, "포인트가 부족합니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
