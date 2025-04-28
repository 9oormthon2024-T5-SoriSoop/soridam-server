package sorisoop.soridam.domain.noise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Radius {
	ONE_HUNDRED_METERS(100),
	TWO_HUNDRED_FIFTY_METERS(250),
	FIVE_HUNDRED_METERS(500),
	ONE_KILOMETER(1000);

	private final int radiusInMeters;
}
