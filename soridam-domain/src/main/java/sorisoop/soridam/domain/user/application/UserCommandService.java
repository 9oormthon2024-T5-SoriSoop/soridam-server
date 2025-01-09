package sorisoop.soridam.domain.user.application;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.domain.UserRepository;

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

		return userRepository.save(user);
	}
}
