package sorisoop.soridam.auth.oauth.kakao;

import static sorisoop.soridam.domain.user.domain.Provider.KAKAO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sorisoop.soridam.auth.oauth.OidcService;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@Service
public class KakaoOidcService extends OidcService {
	@Value("${oidc.client-id.kakao}")
	private String clientId;

	public KakaoOidcService(JpaUserRepository jpaUserRepository) {
		super(jpaUserRepository);
	}

	@Override
	protected String getIssuer() {
		return getProvider().getIssuer();
	}

	@Override
	protected String getJwkSetUri() {
		return getProvider().getJwkSetUri();
	}

	@Override
	protected String getClientId() {
		return clientId;
	}

	@Override
	protected Provider getProvider() {
		return KAKAO;
	}

	@Override
	protected User createNewUser(String identifier, Map<String, Object> claims) {
		String nickname = (String) claims.get("nickname");
		String profileImageUrl = (String) claims.get("picture");

		return User.kakaoOidcCreate(identifier, KAKAO, nickname, profileImageUrl);
	}
}

