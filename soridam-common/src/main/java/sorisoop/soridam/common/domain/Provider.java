package sorisoop.soridam.common.domain;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ProviderNotFoundException;

@Getter
@AllArgsConstructor
public enum Provider {
	KAKAO("https://kauth.kakao.com/.well-known/jwks.json", "https://kauth.kakao.com"),
	GOOGLE("https://www.googleapis.com/oauth2/v3/certs", "https://accounts.google.com"),
	;

	private final String jwkSetUri;
	private final String issuer;

	public static Provider from(String provider) {
		String upperCastedProvider = provider.toUpperCase();

		return Arrays.stream(Provider.values())
			.filter(item -> item.name().equals(upperCastedProvider))
			.findFirst()
			.orElseThrow(ProviderNotFoundException::new);
	}
}
