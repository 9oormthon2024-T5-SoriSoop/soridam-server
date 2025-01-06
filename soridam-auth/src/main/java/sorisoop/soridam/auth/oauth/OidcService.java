package sorisoop.soridam.auth.oauth;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.oauth.exception.OidcExpiredException;
import sorisoop.soridam.auth.oauth.exception.OidcInvalidAudienceException;
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
	protected abstract User createNewUser(String identifier, Map<String, Object> claims);

	private JwtDecoder buildDecoder(String jwkSetUri) {
		return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
	}

	public Map<String, Object> validateAndDecodeIdToken(String idToken) {
		try {
			JwtDecoder jwtDecoder = buildDecoder(getJwkSetUri());
			Jwt jwt = jwtDecoder.decode(idToken);

			Map<String, Object> claims = jwt.getClaims();

			validateIssuer((String) claims.get("iss"));
			validateAudience((List<String>) claims.get("aud"));
			validateExpiration(claims.get("exp"));

			return claims;
		} catch (Exception e) {
			throw new RuntimeException("Failed to validate or decode ID Token", e);
		}
	}

	public User processLogin(OidcLoginRequest idToken) {
		Map<String, Object> claims = validateAndDecodeIdToken(idToken.idToken());
		return findOrCreateUser(claims);
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

	private void validateExpiration(Object expiration) {
		if (expiration instanceof Instant) {
			Instant expInstant = (Instant) expiration;
			if (expInstant.isBefore(Instant.now())) {
				throw new OidcExpiredException();
			}
		} else if (expiration instanceof Long) {
			long expMillis = (Long) expiration * 1000L;
			if (expMillis < System.currentTimeMillis()) {
				throw new OidcExpiredException();
			}
		} else {
			throw new IllegalArgumentException("Unexpected expiration type: " + expiration.getClass());
		}
	}

	protected User findOrCreateUser(Map<String, Object> claims) {
		String identifier = claims.get("sub").toString();
		return jpaUserRepository.findByOAuthIdentityAndProvider(identifier, getProvider())
			.map(existingUser -> {
				existingUser.updateLastLoginTime();
				return jpaUserRepository.save(existingUser);
			})
			.orElseGet(() -> {
				User newUser = createNewUser(identifier, claims);
				newUser.updateLastLoginTime();
				return jpaUserRepository.save(newUser);
			});
	}
}
