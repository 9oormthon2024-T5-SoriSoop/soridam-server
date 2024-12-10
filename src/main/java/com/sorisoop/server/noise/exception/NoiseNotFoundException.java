package com.sorisoop.server.noise.exception;

import static com.sorisoop.server.noise.exception.NoiseExceptionCode.NOISE_NOT_FOUND;

import com.sorisoop.server.common.exception.CustomException;

public class NoiseNotFoundException extends CustomException {
	public NoiseNotFoundException() {
		super(NOISE_NOT_FOUND);
	}
}
