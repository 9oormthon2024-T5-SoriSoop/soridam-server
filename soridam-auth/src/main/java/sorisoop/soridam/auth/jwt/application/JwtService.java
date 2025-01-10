package sorisoop.soridam.auth.jwt.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.jwt.response.JwtResponse;
import sorisoop.soridam.domain.refresh.RefreshToken;
import sorisoop.soridam.domain.refresh.RefreshTokenRepository;
import sorisoop.soridam.domain.refresh.exception.RefreshTokenNotFoundException;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;

@Service
@Transactional
@RequiredArgsConstructor
public class JwtService {
	private final UserQueryService userQueryService;
	private final PasswordEncoder passwordEncoder;
	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtProvider jwtProvider;

	public JwtResponse jwtLogin(String email, String password) {
		User user = userQueryService.getByEmail(email);
		user.isPasswordMatching(password, passwordEncoder);
		user.updateLastLoginTime();
		JwtResponse response = getToken(user);
		saveRefreshToken(user.getId(), response.refreshToken());
		return response;
	}

	public JwtResponse reissue(String token) {
		RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token)
			.orElseThrow(RefreshTokenNotFoundException::new);
		refreshTokenRepository.delete(refreshToken);

		String userId = refreshToken.getUserId();
		User user = userQueryService.getById(userId);
		JwtResponse response = getToken(user);
		saveRefreshToken(userId, response.refreshToken());

		return response;
	}

	public JwtResponse getToken(User user) {
		String refreshToken = jwtProvider.generateRefreshToken(user.getId(), user.getRole());
		String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getRole());

		return JwtResponse.of(accessToken, refreshToken);
	}

	private void saveRefreshToken(String userId, String refreshToken) {
		refreshTokenRepository.save(RefreshToken.of(userId, refreshToken));
	}

}
