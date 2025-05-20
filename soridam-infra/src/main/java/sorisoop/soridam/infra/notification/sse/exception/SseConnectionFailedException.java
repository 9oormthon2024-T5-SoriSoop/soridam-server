package sorisoop.soridam.infra.notification.sse.exception;

import static sorisoop.soridam.infra.notification.sse.exception.SseExceptionCode.SSE_CONNECTION_FAILED;

import sorisoop.soridam.common.exception.CustomException;

public class SseConnectionFailedException extends CustomException {
	public SseConnectionFailedException() {
		super(SSE_CONNECTION_FAILED);
	}
}
