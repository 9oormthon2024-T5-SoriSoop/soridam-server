package sorisoop.soridam.api.auth.application;

import java.time.Duration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.auth.presentation.request.jwt.JwtLoginRequest;
import sorisoop.soridam.api.auth.presentation.response.jwt.JwtResponse;
import sorisoop.soridam.api.user.application.UserService;
import sorisoop.soridam.auth.jwt.JwtProvider;
import sorisoop.soridam.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Transactional(readOnly = true)
	public JwtResponse jwtLogin(JwtLoginRequest request) {
		String email = request.email();
		String password = request.password();

		User user = userService.getByEmail(email);
		user.isPasswordMatching(password, passwordEncoder);

		String refreshToken = jwtProvider.generateToken(user.getId(), user.getRole(), Duration.ofDays(7));
		String accessToken = jwtProvider.generateToken(user.getId(), user.getRole(), Duration.ofHours(2));

		return JwtResponse.of(accessToken, refreshToken);
	}
}
