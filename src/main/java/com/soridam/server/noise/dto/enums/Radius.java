package com.soridam.server.noise.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Radius {
	FIVE_HUNDRED_METERS(500, "500m 이하"),
	ONE_KILOMETER(1000, "1km 이하"),
	TWO_KILOMETERS(2000, "2km 이하");

	private final int radiusInMeters;
	private final String description;
}
