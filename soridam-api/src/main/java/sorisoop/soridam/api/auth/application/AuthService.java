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
import sorisoop.soridam.auth.oauth.request.OidcLoginRequest;
import sorisoop.soridam.auth.oauth.google.GoogleOidcService;
import sorisoop.soridam.auth.oauth.kakao.KakaoOidcService;
import sorisoop.soridam.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final KakaoOidcService kakaoOidcService;
	private final GoogleOidcService googleOidcService;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Transactional(readOnly = true)
	public JwtResponse jwtLogin(JwtLoginRequest request) {
		String email = request.email();
		String password = request.password();

		User user = userService.getByEmail(email);
		user.isPasswordMatching(password, passwordEncoder);

		return getToken(user);
	}

	public JwtResponse googleLogin(OidcLoginRequest idToken) {
		User user = kakaoOidcService.processLogin(idToken);
		return getToken(user);
	}

	public JwtResponse kakaoLogin(OidcLoginRequest idToken) {
		User user = googleOidcService.processLogin(idToken);
		return getToken(user);
	}

	private JwtResponse getToken(User user) {
		String refreshToken = jwtProvider.generateToken(user.getId(), user.getRole(), Duration.ofDays(7));
		String accessToken = jwtProvider.generateToken(user.getId(), user.getRole(), Duration.ofHours(2));

		return JwtResponse.of(accessToken, refreshToken);
	}
}
