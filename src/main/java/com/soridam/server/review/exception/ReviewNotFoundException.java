package com.soridam.server.review.exception;

import static com.soridam.server.review.exception.ReviewExceptionCode.REVIEW_NOT_FOUND;

import com.soridam.server.common.exception.CustomException;

public class ReviewNotFoundException extends CustomException {
	public ReviewNotFoundException() {
		super(REVIEW_NOT_FOUND);
	}
}
