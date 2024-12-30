package sorisoop.soridam.auth.oauth;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.exception.ProviderNotFoundException;

@Slf4j
public class OicdUserFactory {
	public static User getOAuth2UserInfo(Provider providerInfo, Map<String, Object> attributes) {
		switch (providerInfo) {
			case KAKAO -> {
				String identifier = attributes.get("sub").toString();
				String nickname = attributes.get("nickname").toString();
				String profileImageUrl = attributes.get("picture").toString();

				return User.kakaoOicdCreate(identifier, providerInfo, nickname, profileImageUrl);
			}
			case GOOGLE -> {
				String identifier = attributes.get("sub").toString();
				String profileImageUrl = attributes.get("picture").toString();
				String email = attributes.get("email").toString();
				String name = attributes.get("name").toString();

				return User.googleOicdCreate(identifier, providerInfo, name, email, profileImageUrl);
			}
		}
		throw new ProviderNotFoundException();
	}
}