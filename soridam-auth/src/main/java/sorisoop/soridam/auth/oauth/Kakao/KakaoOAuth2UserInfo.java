package sorisoop.soridam.auth.oauth.Kakao;

import java.util.Map;

import sorisoop.soridam.auth.oauth.OAuth2UserInfo;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

	public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getOAuth2Id() {
		return attributes.get("id").toString();
	}

	@Override
	public String getEmail() {
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
		return (String) account.get("email");
	}
}

