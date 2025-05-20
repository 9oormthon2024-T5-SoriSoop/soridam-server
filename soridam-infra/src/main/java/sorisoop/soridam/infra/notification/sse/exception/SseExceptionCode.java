package sorisoop.soridam.infra.notification.sse.exception;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum SseExceptionCode implements ExceptionCode {
	SSE_CONNECTION_FAILED(SERVICE_UNAVAILABLE, "SSE 연결에 실패했습니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
