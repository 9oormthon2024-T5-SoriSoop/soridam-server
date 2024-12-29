package sorisoop.soridam.auth.oauth;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sorisoop.soridam.domain.user.domain.User;

@Getter
@Builder
@AllArgsConstructor
public class UserPrincipal implements OAuth2User {

	private User user;
	private String nameAttributeKey;
	private Map<String, Object> attributes;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(User user) {
		this.user = user;
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getDescription()));
	}

	public UserPrincipal() {
		this.user = user;
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getDescription()));
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
	}

	/**
	 * OAuth2User method implements
	 */
	@Override
	public String getName() {
		return user.getId();
	}

	public static UserPrincipal create(User user, Map<String, Object> attributes, String nameAttributeKey) {
		return UserPrincipal.builder()
			.user(user)
			.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getDescription())))
			.attributes(attributes)
			.nameAttributeKey(nameAttributeKey)
			.build();
	}
}