package sorisoop.soridam.api.auth.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.auth.presentation.request.jwt.JwtLoginRequest;
import sorisoop.soridam.api.auth.presentation.request.jwt.RefreshTokenRequest;
import sorisoop.soridam.api.auth.presentation.request.oauth.OidcLoginRequest;
import sorisoop.soridam.auth.jwt.application.JwtService;
import sorisoop.soridam.auth.jwt.response.JwtResponse;
import sorisoop.soridam.auth.oauth.google.GoogleOidcService;
import sorisoop.soridam.auth.oauth.kakao.KakaoOidcService;
import sorisoop.soridam.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class AuthFacade {
	private final KakaoOidcService kakaoOidcService;
	private final GoogleOidcService googleOidcService;
	private final JwtService jwtService;

	public JwtResponse jwtLogin(JwtLoginRequest request) {
		return jwtService.jwtLogin(request.email(), request.password());
	}

	public JwtResponse kakaoLogin(OidcLoginRequest idToken) {
		User user = kakaoOidcService.processLogin(idToken.idToken());
		user.updateLastLoginTime();
		return jwtService.getToken(user);
	}

	public JwtResponse googleLogin(OidcLoginRequest idToken) {
		User user = googleOidcService.processLogin(idToken.idToken());
		user.updateLastLoginTime();
		return jwtService.getToken(user);
	}

	public JwtResponse reissue(RefreshTokenRequest request) {
		return jwtService.reissue(request.refreshToken());
	}
}
