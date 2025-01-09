package sorisoop.soridam.domain.user.application;

import static sorisoop.soridam.globalutil.uuid.UuidPrefix.USER;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.domain.UserRepository;
import sorisoop.soridam.domain.user.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserQueryService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<Noise> getUserNoises(String id) {
		User user = getById(id);
		return user.getNoises();
	}

	public User getById(String id) {
		return userRepository.findById(USER.getPrefix() + id)
			.orElseThrow(UserNotFoundException::new);
	}

	public User getByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(UserNotFoundException::new);
	}
}
