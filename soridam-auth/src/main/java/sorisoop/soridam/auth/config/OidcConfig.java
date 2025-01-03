package sorisoop.soridam.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.oauth.google.GoogleOidcProperties;
import sorisoop.soridam.auth.oauth.kakao.KakaoOidcProperties;

@Configuration
@RequiredArgsConstructor
public class OidcConfig {

	private final GoogleOidcProperties googleOidcProperties;
	private final KakaoOidcProperties kakaoOidcProperties;

	/**
	 * Google OIDC 토큰 검증용 JwtDecoder
	 */
	@Bean
	public JwtDecoder googleJwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(googleOidcProperties.getJwkSetUri()).build();
	}

/**
 * Kakao OIDC 토큰 검증용 Jwt
