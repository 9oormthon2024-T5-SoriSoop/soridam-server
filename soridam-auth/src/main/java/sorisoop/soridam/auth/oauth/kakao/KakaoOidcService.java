package sorisoop.soridam.auth.oauth.kakao;

import static sorisoop.soridam.domain.user.domain.Provider.KAKAO;

import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.oauth.google.OidcService;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@Service
@RequiredArgsConstructor
public class KakaoOidcService extends OidcService {
	private final JpaUserRepository jpaUserRepository;
	private final KakaoOidcProperties kakaoOidcProperties;

	@Override
	protected String getIssuer() {
		return kakaoOidcProperties.getIssuer();
	}

	@Override
	protected String getJwkSetUri() {
		return kakaoOidcProperties.getJwkSetUri();
	}

	@Override
	protected String getClientId() {
		return kakaoOidcProperties.getClientId();
	}

	@Override
	protected User extractUserFromClaims(Map<String, Object> claims) {
		String identifier = (String) claims.get("sub");
		String nickname = (String) claims.get("nickname");
		String profileImageUrl = (String) claims.get("picture");

		return jpaUserRepository.findByOAuthIdentityAndProvider(identifier, KAKAO)
			.orElseGet(() -> {
				User newUser = User.kakaoOicdCreate(identifier, KAKAO, nickname, profileImageUrl);
				return jpaUserRepository.save(newUser);
			});
	}
}

