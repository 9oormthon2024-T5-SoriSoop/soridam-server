package sorisoop.soridam.infra.uuid;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UuidPrefix {
	USER("usr_"),
	NOISE("nse_"),
	SOUND("snd_"),
	IMAGE("img_"),
	DEFAULT("daf_");

	private final String prefix;
}
