package sorisoop.soridam.infra.openai.exception;

import static sorisoop.soridam.infra.openai.exception.OpenAiExceptionCode.OPENAI_REQUEST_FAILED;

import sorisoop.soridam.common.exception.CustomException;

public class OpenAiRequestException extends CustomException {
	public OpenAiRequestException() {
		super(OPENAI_REQUEST_FAILED);
	}
}
