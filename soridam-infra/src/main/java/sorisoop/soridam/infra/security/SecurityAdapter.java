package sorisoop.soridam.infra.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import sorisoop.soridam.domain.user.infrastructure.SecurityPort;

@Component
public class SecurityAdapter implements SecurityPort {

	@Override
	public String getCurrentUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		throw new IllegalStateException("No authenticated user found");
	}
}
