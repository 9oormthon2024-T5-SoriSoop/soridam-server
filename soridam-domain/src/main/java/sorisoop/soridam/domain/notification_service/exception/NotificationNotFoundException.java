package sorisoop.soridam.domain.notification_service.exception;

import static sorisoop.soridam.domain.notification_service.exception.NotificationExceptionCode.NOTIFICATION_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class NotificationNotFoundException extends CustomException {
	public NotificationNotFoundException() {
		super(NOTIFICATION_NOT_FOUND);
	}
}
