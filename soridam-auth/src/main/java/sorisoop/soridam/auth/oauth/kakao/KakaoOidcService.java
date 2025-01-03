package sorisoop.soridam.auth.oauth.kakao;

import org.springframework.stereotype.Service;

import sorisoop.soridam.auth.oauth.request.OidcLoginRequest;
import sorisoop.soridam.domain.user.domain.User;

@Service
public class KakaoOidcService {
	public User processLogin(OidcLoginRequest idToken) {
	}
}
