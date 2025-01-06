package sorisoop.soridam.auth.oauth.google;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "oidc.google")
public class GoogleOidcProperties {
	private String jwkSetUri;
	private String issuer;
	private String clientId;
}
