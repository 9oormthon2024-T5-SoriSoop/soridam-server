package sorisoop.soridam.auth.oauth.google;

import static sorisoop.soridam.domain.user.domain.Provider.GOOGLE;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@Service
@RequiredArgsConstructor
public class GoogleOidcService extends OidcService {
	private final JpaUserRepository jpaUserRepository;
	private final GoogleOidcProperties googleOidcProperties;

	@Override
	protected String getIssuer() {
		return googleOidcProperties.getIssuer();
	}

	@Override
	protected String getJwkSetUri() {
		return googleOidcProperties.getJwkSetUri();
	}

	@Override
	protected String getClientId() {
		return googleOidcProperties.getClientId();
	}

	@Override
	protected User extractUserFromClaims(Map<String, Object> claims) {
		String identifier = claims.get("sub").toString();
		String name = (String) claims.get("name");
		String email = (String) claims.get("email");
		String profileImageUrl = (String) claims.get("picture");

		Optional<User> userOptional = jpaUserRepository.findByOAuthIdentityAndProvider(identifier, GOOGLE);

		return userOptional.orElseGet(() -> {
			User newUser = User.googleOicdCreate(identifier, GOOGLE, name, email, profileImageUrl);
			return jpaUserRepository.save(newUser);
		});	}
}


