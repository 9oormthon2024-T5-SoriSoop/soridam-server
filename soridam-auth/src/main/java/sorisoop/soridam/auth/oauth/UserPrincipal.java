package sorisoop.soridam.auth.oauth;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sorisoop.soridam.domain.user.domain.User;

@Getter
@Builder
@AllArgsConstructor
public class UserPrincipal implements OidcUser {

	private User user;
	private OidcIdToken idToken;

	@Override
	public String getName() {
		return user.getId();
	}

	public static UserPrincipal create(User user, OidcIdToken idToken) {
		return UserPrincipal.builder()
			.user(user)
			.idToken(idToken)
			.build();
	}

	@Override
	public Map<String, Object> getClaims() {
		return idToken.getClaims();
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return new OidcUserInfo(idToken.getClaims());
	}

	@Override
	public OidcIdToken getIdToken() {
		return idToken;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return idToken.getClaims();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> user.getRole().getDescription());
	}
}