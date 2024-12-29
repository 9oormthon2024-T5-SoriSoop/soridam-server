package sorisoop.soridam.auth.oauth;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService {
	private final JpaUserRepository jpaUserRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String providerCode = userRequest.getClientRegistration().getRegistrationId();
		Provider provider = Provider.from(providerCode);

		Map<String, Object> attributes = oAuth2User.getAttributes();
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
			.getUserInfoEndpoint().getUserNameAttributeName();

		User user = getUser(provider, attributes);

		return UserPrincipal.create(user, attributes, userNameAttributeName);
	}

	private User getUser(Provider provider, Map<String, Object> attributes) {
		User newUser = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, attributes);

		return jpaUserRepository.findByOAuthIdentityAndProvider(newUser.getOAuthIdentity(), provider)
			.orElseGet(() -> jpaUserRepository.save(newUser));
	}
}
