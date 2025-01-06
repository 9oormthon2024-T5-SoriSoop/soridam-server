package sorisoop.soridam.auth.oauth.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "oidc.kakao")
public class KakaoOidcProperties {
	private String jwkSetUri;
	private String issuer;
	private String clientId;
}
