package sorisoop.soridam.domain.user.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.domain.UserRepository;
import sorisoop.soridam.domain.user.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserCommandService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public User signUp(String email, String password, String name, String nickname){
		User user = User.create(
			email,
			bCryptPasswordEncoder.encode(password),
			name,
			nickname
		);

		return userRepository.save(user);
	}

	public User login(String email, String password){
		User user = userRepository.findByEmail(email)
			.orElseThrow(UserNotFoundException::new);
		user.isPasswordMatching(password, bCryptPasswordEncoder);
		user.updateLastLoginTime();

		return user;
	}
}
