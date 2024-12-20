package sorisoop.soridam.api.noise.presentation.exception;

import static sorisoop.soridam.api.noise.presentation.exception.NoiseExceptionCode.NOISE_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class NoiseNotFoundException extends CustomException {
	public NoiseNotFoundException() {
		super(NOISE_NOT_FOUND);
	}
}
