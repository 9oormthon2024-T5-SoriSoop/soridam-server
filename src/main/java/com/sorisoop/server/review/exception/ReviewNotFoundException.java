package com.sorisoop.server.review.exception;

import static com.sorisoop.server.review.exception.ReviewExceptionCode.REVIEW_NOT_FOUND;

import com.sorisoop.server.common.exception.CustomException;

public class ReviewNotFoundException extends CustomException {
	public ReviewNotFoundException() {
		super(REVIEW_NOT_FOUND);
	}
}
