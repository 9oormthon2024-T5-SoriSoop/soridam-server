package sorisoop.soridam.domain.review_service.exception;

import static sorisoop.soridam.domain.review_service.exception.ReviewExceptionCode.REVIEW_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class ReviewNotFoundException extends CustomException {
	public ReviewNotFoundException() {
		super(REVIEW_NOT_FOUND);
	}
}
