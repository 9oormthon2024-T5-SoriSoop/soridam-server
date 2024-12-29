package sorisoop.soridam.domain.user.domain;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.domain.user.exception.ProviderNotFoundException;

@Getter
@AllArgsConstructor
public enum Provider {
	KAKAO("kakao_account", "id", "email"),
	GOOGLE(null, "sub", "email"),
	;

	private final String attributeKey;
	private final String providerCode;
	private final String identifier;

	public static Provider from(String provider) {
		String upperCastedProvider = provider.toUpperCase();

		return Arrays.stream(Provider.values())
			.filter(item -> item.name().equals(upperCastedProvider))
			.findFirst()
			.orElseThrow(ProviderNotFoundException::new);
	}
}
