package sorisoop.soridam.domain.user.application;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.exception.UserNotFoundException;
import sorisoop.soridam.domain.user.infrastructure.PasswordEncoder;
import sorisoop.soridam.domain.user.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class UserCommandService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public User signUp(String email, String password, String name, String nickname,
		LocalDate birthDate, String phoneNumber, String profileImageUrl){
		User user = User.create(
			email,
			passwordEncoder.encode(password),
			name,
			nickname,
			birthDate,
			phoneNumber,
			profileImageUrl
		);

		userRepository.save(user);

		return user;
	}

	public User login(String email, String password){
		User user = userRepository.findByEmail(email)
			.orElseThrow(UserNotFoundException::new);
		user.isPasswordMatching(password, passwordEncoder);
		user.updateLastLoginTime();

		return user;
	}
}
