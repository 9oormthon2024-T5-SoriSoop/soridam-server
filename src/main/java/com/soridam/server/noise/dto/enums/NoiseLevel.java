package com.soridam.server.noise.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoiseLevel {
	QUIET(0, 70, "조용함"),
	NORMAL(70, 100, "보통"),
	LOUD(100, 120, "시끄러움");

	private final int minDecibel;
	private final int maxDecibel;
	private final String description;
}
