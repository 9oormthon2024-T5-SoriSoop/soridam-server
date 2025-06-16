package sorisoop.soridam.domain.place.noise.exception;

import static sorisoop.soridam.domain.place.noise.exception.NoiseExceptionCode.NOISE_NOT_FOUND;

import sorisoop.soridam.common.exception.CustomException;

public class NoiseNotFoundException extends CustomException {
	public NoiseNotFoundException() {
		super(NOISE_NOT_FOUND);
	}
}
