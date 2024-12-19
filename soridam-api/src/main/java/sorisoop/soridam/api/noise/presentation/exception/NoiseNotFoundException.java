package sorisoop.soridam.api.noise.presentation.exception;

import sorisoop.soridam.common.exception.CustomException;

public class NoiseNotFoundException extends CustomException {
	public NoiseNotFoundException() {
		super(NoiseExceptionCode.NOISE_NOT_FOUND);
	}
}
