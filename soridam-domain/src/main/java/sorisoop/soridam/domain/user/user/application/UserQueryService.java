package sorisoop.soridam.domain.user.user.application;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.user.domain.User;
import sorisoop.soridam.domain.user.user.domain.UserRepository;
import sorisoop.soridam.domain.user.user.exception.UnauthorizedException;
import sorisoop.soridam.domain.user.user.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserQueryService {
	private final UserRepository userRepository;

	public User getById(Long id) {
		return userRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);
	}

	public User getByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(UserNotFoundException::new);
	}

	public User me() {
		try{
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Long userId = Long.valueOf(((UserDetails)principal).getUsername());
			return getById(userId);
		} catch (Exception e){
			throw new UnauthorizedException();
		}
	}
}
