package com.soridam.server.review.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import com.soridam.server.common.exception.ExceptionCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewExceptionCode implements ExceptionCode {
	REVIEW_NOT_FOUND(NOT_FOUND, "해당 리뷰를 찾을 수 없습니다."),
	;
	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return "";
	}
}
