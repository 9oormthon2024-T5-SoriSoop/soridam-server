package sorisoop.soridam.domain.noise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Radius {
	FIVE_HUNDRED_METERS(500),
	ONE_KILOMETER(1000),
	TWO_KILOMETERS(2000);

	private final int radiusInMeters;
}
