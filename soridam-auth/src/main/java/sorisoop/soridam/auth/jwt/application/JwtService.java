package sorisoop.soridam.auth.jwt.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.jwt.response.JwtResponse;
import sorisoop.soridam.domain.refresh.RefreshToken;
import sorisoop.soridam.domain.refresh.application.RefreshTokenService;
import sorisoop.soridam.domain.user.application.UserCommandService;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class JwtService {
	private final UserQueryService userQueryService;
	private final UserCommandService userCommandService;
	private final RefreshTokenService refreshTokenService;
	private final JwtProvider jwtProvider;

	public JwtResponse jwtLogin(String email, String password) {
		User user = userCommandService.login(email, password);
		JwtResponse response = getToken(user);
		refreshTokenService.save(user.getId(), response.refreshToken());
		return response;
	}

	public JwtResponse reissue(String token) {
		RefreshToken refreshToken = refreshTokenService.getToken(token);
		String userId = refreshToken.getUserId();
		User user = userQueryService.getById(userId);
		JwtResponse response = getToken(user);

		refreshTokenService.delete(refreshToken);
		refreshTokenService.save(userId, response.refreshToken());

		return response;
	}

	public JwtResponse getToken(User user) {
		String refreshToken = jwtProvider.generateRefreshToken(user.extractUuid(), user.getRole());
		String accessToken = jwtProvider.generateAccessToken(user.extractUuid(), user.getRole());

		return JwtResponse.of(accessToken, refreshToken);
	}

}
