package sorisoop.soridam.auth.oauth;

import java.util.Map;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {
	private final JpaUserRepository jpaUserRepository;

	@Override
	@Transactional
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = super.loadUser(userRequest);
		OidcIdToken idToken = oidcUser.getIdToken();

		String providerCode = userRequest.getClientRegistration().getRegistrationId();
		Provider provider = Provider.from(providerCode);

		Map<String, Object> attributes = oidcUser.getClaims();
		User user = getUserAndUpdateIfNeeded(provider, attributes);

		return UserPrincipal.create(user, idToken);
	}

	private User getUserAndUpdateIfNeeded(Provider provider, Map<String, Object> attributes) {
		String oAuthIdentity = attributes.get("sub").toString();

		return jpaUserRepository.findByOAuthIdentityAndProvider(oAuthIdentity, provider)
			.map(existingUser -> {
				updateUserInfo(existingUser, attributes);
				return existingUser;
			})
			.orElseGet(() -> {
				User newUser = OicdUserFactory.getOAuth2UserInfo(provider, attributes);
				newUser.updateLastLoginTime();
				return jpaUserRepository.save(newUser);
			});
	}

	private void updateUserInfo(User user, Map<String, Object> attributes) {
		String newProfileImageUrl = (String) attributes.get("picture");
		if (newProfileImageUrl != null && !newProfileImageUrl.equals(user.getProfileImageUrl())) {
			user.updateProfileImageUrl(newProfileImageUrl);
		}

		user.updateLastLoginTime();
	}
}
