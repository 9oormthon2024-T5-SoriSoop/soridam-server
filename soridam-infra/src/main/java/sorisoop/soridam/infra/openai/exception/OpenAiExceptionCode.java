package sorisoop.soridam.infra.openai.exception;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum OpenAiExceptionCode implements ExceptionCode {
	OPENAI_REQUEST_FAILED(BAD_GATEWAY, "OpenAI 응답에 실패했습니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
