package sorisoop.soridam.api.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.auth.presentation.request.jwt.JwtLoginRequest;
import sorisoop.soridam.api.auth.presentation.request.jwt.RefreshTokenRequest;
import sorisoop.soridam.api.auth.presentation.request.oauth.OidcLoginRequest;
import sorisoop.soridam.auth.jwt.application.JwtProvider;
import sorisoop.soridam.auth.jwt.response.JwtResponse;
import sorisoop.soridam.auth.oauth.google.GoogleOidcService;
import sorisoop.soridam.auth.oauth.kakao.KakaoOidcService;
import sorisoop.soridam.domain.user.refresh.application.RefreshTokenService;
import sorisoop.soridam.domain.user.refresh.domain.RefreshToken;
import sorisoop.soridam.domain.user.user.application.UserCommandService;
import sorisoop.soridam.domain.user.user.application.UserQueryService;
import sorisoop.soridam.domain.user.user.domain.User;

@Service
@RequiredArgsConstructor
public class AuthFacade {
	private final KakaoOidcService kakaoOidcService;
	private final GoogleOidcService googleOidcService;
	private final JwtProvider jwtProvider;
	private final UserQueryService userQueryService;
	private final UserCommandService userCommandService;
	private final RefreshTokenService refreshTokenService;

	@Transactional
	public JwtResponse jwtLogin(JwtLoginRequest request) {
		User user = userQueryService.getByEmail(request.email());
		userCommandService.login(user, request.password());
		JwtResponse response = getToken(user);
		refreshTokenService.save(user.getId(), response.refreshToken());

		return response;
	}

	@Transactional
	public JwtResponse kakaoLogin(OidcLoginRequest idToken) {
		User user = kakaoOidcService.processLogin(idToken.idToken());
		user.updateLastLoginTime();
		return getToken(user);
	}

	@Transactional
	public JwtResponse googleLogin(OidcLoginRequest idToken) {
		User user = googleOidcService.processLogin(idToken.idToken());
		user.updateLastLoginTime();
		return getToken(user);
	}

	@Transactional(readOnly = true)
	public JwtResponse reissue(RefreshTokenRequest request) {
		RefreshToken refreshToken = refreshTokenService.getToken(request.refreshToken());
		Long userId = refreshToken.getUserId();

		User user = userQueryService.getById(userId);
		JwtResponse response = getToken(user);
		refreshTokenService.save(userId, response.refreshToken());
		return response;
	}

	private JwtResponse getToken(User user) {
		String refreshToken = jwtProvider.generateRefreshToken(user.getId(), user.getRole());
		String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getRole());

		return JwtResponse.of(accessToken, refreshToken);
	}
}
