package sorisoop.soridam.common.uuid;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UuidPrefix {
	USER("user_"),
	NOISE("noise_"),
	SOUND("sound_"),
	IMAGE("img_"),
	DEFAULT("def_");

	private final String prefix;
}
