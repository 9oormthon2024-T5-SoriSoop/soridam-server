package sorisoop.soridam.auth.oauth;

import java.util.Map;

import sorisoop.soridam.auth.oauth.Kakao.KakaoOAuth2UserInfo;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.exception.ProviderNotFoundException;

public class OAuth2UserInfoFactory {
	public static OAuth2UserInfo getOAuth2UserInfo(Provider providerInfo, Map<String, Object> attributes) {
		switch (providerInfo) {
			case KAKAO -> {
				return new KakaoOAuth2UserInfo(attributes);
			}
		}
		throw new ProviderNotFoundException();
	}
}