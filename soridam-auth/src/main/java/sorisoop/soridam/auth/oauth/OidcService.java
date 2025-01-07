package sorisoop.soridam.auth.oauth;

import java.time.Instant;
import java.util.List;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.oauth.exception.OidcExpiredException;
import sorisoop.soridam.auth.oauth.exception.OidcInvalidAudienceException;
import sorisoop.soridam.auth.oauth.exception.OidcInvalidIdTokenException;
import sorisoop.soridam.auth.oauth.exception.OidcInvalidIssuerException;
import sorisoop.soridam.auth.oauth.request.OidcLoginRequest;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@RequiredArgsConstructor
public abstract class OidcService {
	private final JpaUserRepository jpaUserRepository;

	protected abstract String getIssuer();
	protected abstract String getJwkSetUri();
	protected abstract String getClientId();
	protected abstract Provider getProvider();
	protected abstract User createNewUser(String identifier, OidcUserInfo oidcUserInfo);

	private JwtDecoder buildDecoder(String jwkSetUri) {
		return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
	}

	private OidcIdToken validateAndDecodeIdToken(String idToken) {
		try {
			JwtDecoder jwtDecoder = buildDecoder(getJwkSetUri());
			Jwt jwt = jwtDecoder.decode(idToken);
			OidcIdToken oidcIdToken = getOidcIdToken(jwt);

			validateIssuer(oidcIdToken.getIssuer().toString());
			validateAudience(oidcIdToken.getAudience());
			validateExpiration(oidcIdToken.getExpiresAt());

			return oidcIdToken;
		} catch (Exception e) {
			throw new OidcInvalidIdTokenException();
		}
	}

	public User processLogin(OidcLoginRequest request) {
		OidcIdToken idToken = validateAndDecodeIdToken(request.idToken());
		return findOrCreateUser(idToken);
	}

	private void validateIssuer(String issuer) {
		if (!getIssuer().equals(issuer)) {
			throw new OidcInvalidIssuerException();
		}
	}

	private void validateAudience(List<String> audience) {
		if (audience == null || !audience.contains(getClientId())) {
			throw new OidcInvalidAudienceException();
		}
	}

	private void validateExpiration(Instant expiration) {
		if (expiration.isBefore(Instant.now())) {
			throw new OidcExpiredException();
		}
	}

	private OidcIdToken getOidcIdToken(Jwt jwt) {
		return new OidcIdToken(
			jwt.getTokenValue(), jwt.getIssuedAt(), jwt.getExpiresAt(), jwt.getClaims());
	}

	protected User findOrCreateUser(OidcIdToken idToken) {
		String identifier = idToken.getSubject();
		return jpaUserRepository.findByOauthIdentityAndProvider(identifier, getProvider())
			.map(existingUser -> {
				existingUser.updateLastLoginTime();
				return jpaUserRepository.save(existingUser);
			})
			.orElseGet(() -> {
				User newUser = createNewUser(identifier, new OidcUserInfo(idToken.getClaims()));
				newUser.updateLastLoginTime();
				return jpaUserRepository.save(newUser);
			});
	}
}
