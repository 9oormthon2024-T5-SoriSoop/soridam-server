package com.soridam.server.noise.exception;

import static com.soridam.server.noise.exception.NoiseExceptionCode.NOISE_NOT_FOUND;

import com.soridam.server.common.exception.CustomException;

public class NoiseNotFoundException extends CustomException {
	public NoiseNotFoundException() {
		super(NOISE_NOT_FOUND);
	}
}
