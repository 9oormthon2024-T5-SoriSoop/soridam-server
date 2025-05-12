package sorisoop.soridam.domain.notification.exception;

import static sorisoop.soridam.domain.notification.exception.NotificationExceptionCode.NOTIFICATION_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class NotificationNotFoundException extends CustomException {
	public NotificationNotFoundException() {
		super(NOTIFICATION_NOT_FOUND);
	}
}
