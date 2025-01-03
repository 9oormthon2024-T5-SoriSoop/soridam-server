package sorisoop.soridam.auth.oauth.google;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.oauth.request.OidcLoginRequest;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@Service
@RequiredArgsConstructor
public class GoogleOidcService {
	private final JpaUserRepository userRepository;
	private final GoogleOidcProperties googleOidcProperties;
	private final RestTemplate restTemplate;

	public User processLogin(OidcLoginRequest idToken) {
		// 1. 토큰 검증
		Map<String, Object> claims = validateIdToken(idToken);

		// 2. 사용자 정보 추출
		String identifier = claims.get("sub").toString();
		String email = claims.get("email").toString();
		String name = claims.get("name").toString();
		String profileImageUrl = claims.get("picture").toString();

		// 3. 데이터베이스에서 사용자 조회
		return userRepository.findByOAuthIdentityAndProvider(identifier, Provider.GOOGLE)
			.orElseGet(() -> {
				// 4. 사용자 정보가 없으면 저장
				User newUser = User.googleOicdCreate(identifier, Provider.GOOGLE, name, email, profileImageUrl);
				return userRepository.save(newUser);
			});
	}

	private Map<String, Object> validateIdToken(OidcLoginRequest idToken) {
		String token = idToken.getIdToken();

		// Google의 JWK Set URI로 토큰 검증
		var jwkDecoder = NimbusJwtDecoder.withJwkSetUri(googleOidcProperties.getJwkSetUri()).build();
		Jwt decodedJwt = jwkDecoder.decode(token);

		// 토큰의 클레임 추출
		validateIssuer(decodedJwt.getClaim("iss").toString());
		validateAudience(decodedJwt.getAudience());
		validateExpiration(decodedJwt.getExpiresAt());

		return decodedJwt.getClaims();
	}

	private void validateIssuer(String issuer) {
		if (!googleOidcProperties.getIssuerUri().equals(issuer)) {
			throw new IllegalArgumentException("Invalid token issuer");
		}
	}

	private void validateAudience(List<String> audience) {
		if (!audience.contains(googleOidcProperties.getClientId())) {
			throw new IllegalArgumentException("Invalid token audience");
		}
	}

	private void validateExpiration(Instant expiresAt) {
		if (expiresAt.isBefore(Instant.now())) {
			throw new IllegalArgumentException("Token is expired");
		}
	}
}

