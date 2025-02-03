package sorisoop.soridam.domain.user.application;

import static sorisoop.soridam.globalutil.uuid.UuidPrefix.USER;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.exception.UnauthorizedException;
import sorisoop.soridam.domain.user.exception.UserNotFoundException;
import sorisoop.soridam.domain.user.infrastructure.SecurityPort;
import sorisoop.soridam.domain.user.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class UserQueryService {
	private final UserRepository userRepository;
	private final SecurityPort securityPort;

	@Transactional(readOnly = true)
	public User getUserInfo(String id) {
		return getDocumentById(id);
	}

	public User getById(String id) {
		return userRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);
	}

	public User getDocumentById(String id) {
		return userRepository.findUserDocumentById(id)
			.orElseThrow(UserNotFoundException::new);
	}

	public User me() {
		try{
			String userId = securityPort.getCurrentUserId();
			return getById(USER.getPrefix() + userId);
		} catch (Exception e){
			throw new UnauthorizedException();
		}
	}
}
