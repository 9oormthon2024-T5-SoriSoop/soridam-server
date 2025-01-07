package sorisoop.soridam.auth.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.oauth.google.GoogleOidcProperties;
import sorisoop.soridam.auth.oauth.kakao.KakaoOidcProperties;

@Configuration
@EnableConfigurationProperties({
	GoogleOidcProperties.class,
	KakaoOidcProperties.class
})
@RequiredArgsConstructor
public class OidcConfig {
}
