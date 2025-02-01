package sorisoop.soridam.domain.user.command.application;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.command.domain.User;
import sorisoop.soridam.domain.user.exception.UserNotFoundException;
import sorisoop.soridam.domain.user.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class UserCommandService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public User signUp(String email, String password, String name, String nickname,
		LocalDate birthDate, String phoneNumber, String profileImageUrl){
		User user = User.create(
			email,
			bCryptPasswordEncoder.encode(password),
			name,
			nickname,
			birthDate,
			phoneNumber,
			profileImageUrl
		);

		userRepository.save(user);
		userRepository.flush();
		return user;
	}

	public User login(String email, String password){
		User user = userRepository.findByEmail(email)
			.orElseThrow(UserNotFoundException::new);
		user.isPasswordMatching(password, bCryptPasswordEncoder);
		user.updateLastLoginTime();

		return user;
	}
}
