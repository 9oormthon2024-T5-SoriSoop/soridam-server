package sorisoop.soridam.domain.user.query.application;

import static sorisoop.soridam.globalutil.uuid.UuidPrefix.USER;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.command.domain.User;
import sorisoop.soridam.domain.user.exception.UnauthorizedException;
import sorisoop.soridam.domain.user.exception.UserNotFoundException;
import sorisoop.soridam.domain.user.infrastructure.UserRepository;
import sorisoop.soridam.domain.user.query.domain.UserDocument;

@Service
@RequiredArgsConstructor
public class UserQueryService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public UserDocument getUserInfo(String id) {
		return getDocumentById(id);
	}

	public User getById(String id) {
		return userRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);
	}

	public UserDocument getDocumentById(String id) {
		return userRepository.findUserDocumentById(id)
			.orElseThrow(UserNotFoundException::new);
	}

	public User me() {
		try{
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userId = ((UserDetails)principal).getUsername();
			return getById(USER.getPrefix() + userId);
		} catch (Exception e){
			throw new UnauthorizedException();
		}
	}
}
