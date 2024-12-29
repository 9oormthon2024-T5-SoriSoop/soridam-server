package sorisoop.soridam.auth.oauth;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.exception.ProviderNotFoundException;

@Slf4j
public class OAuth2UserInfoFactory {
	public static User getOAuth2UserInfo(Provider providerInfo, Map<String, Object> attributes) {
		switch (providerInfo) {
			case KAKAO -> {
				String identifier = attributes.get("id").toString();
				Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
				if (properties == null) {
					throw new IllegalArgumentException("properties information is missing in the attributes");
				}
				log.info("attributes : {}", properties);
				String nickname = properties.get("nickname").toString();
				String profileImageUrl = properties.get("profile_image").toString();

				return User.kakaoOAuthCreate(identifier, providerInfo, nickname, profileImageUrl);
			}
		}
		throw new ProviderNotFoundException();
	}
}