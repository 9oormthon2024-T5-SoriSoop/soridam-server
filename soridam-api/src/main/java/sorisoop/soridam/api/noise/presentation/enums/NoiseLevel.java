package sorisoop.soridam.api.noise.presentation.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoiseLevel {
	QUIET(0, 50, "조용함"),
	NORMAL(51, 70, "보통"),
	LOUD(71, 120, "시끄러움");

	private final int minDecibel;
	private final int maxDecibel;
	private final String description;

	public static NoiseLevel from(int decibel) {
		return Arrays.stream(values())
			.filter(level -> decibel >= level.minDecibel && decibel <= level.maxDecibel)
			.findFirst()
			.orElse(LOUD);
	}
}
