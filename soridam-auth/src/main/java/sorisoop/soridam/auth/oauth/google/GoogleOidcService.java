package sorisoop.soridam.auth.oauth.google;

import static sorisoop.soridam.domain.user.domain.Provider.GOOGLE;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sorisoop.soridam.auth.oauth.OidcService;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@Service
public class GoogleOidcService extends OidcService {
	@Value("${oidc.client-id.kakao}")
	private String clientId;

	public GoogleOidcService(JpaUserRepository jpaUserRepository) {
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
		return GOOGLE;
	}

	@Override
	protected User createNewUser(String identifier, Map<String, Object> claims) {
		String name = (String) claims.get("name");
		String email = (String) claims.get("email");
		String profileImageUrl = (String) claims.get("picture");

		return User.googleOidcCreate(identifier, GOOGLE, name, email, profileImageUrl);
	}
}


