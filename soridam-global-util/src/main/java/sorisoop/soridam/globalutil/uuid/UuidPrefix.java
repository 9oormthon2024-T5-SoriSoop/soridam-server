package sorisoop.soridam.globalutil.uuid;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UuidPrefix {
	USER("user-"),
	NOISE("noise-"),
	SOUND("sound-"),
	IMAGE("img-"),
	DEFAULT("def-");

	private final String prefix;
}
