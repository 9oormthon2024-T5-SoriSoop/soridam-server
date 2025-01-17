package sorisoop.soridam.auth.oauth.google;

import static sorisoop.soridam.common.domain.Provider.GOOGLE;

import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import sorisoop.soridam.auth.oauth.OidcService;
import sorisoop.soridam.common.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.domain.UserRepository;

@Service
public class GoogleOidcService extends OidcService {
	private final GoogleOidcProperties properties;

	public GoogleOidcService(UserRepository userRepository, GoogleOidcProperties properties) {
		super(userRepository);
		this.properties = properties;
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
		return properties.getClientId();
	}

	@Override
	protected Provider getProvider() {
		return GOOGLE;
	}

	@Override
	protected User createNewUser(String identifier, OidcUserInfo oidcUserInfo) {
		String name = oidcUserInfo.getFullName();
		String email = oidcUserInfo.getEmail();
		String profileImageUrl = oidcUserInfo.getPicture();

		return User.googleOidcCreate(identifier, GOOGLE, name, email, profileImageUrl);
	}
}


