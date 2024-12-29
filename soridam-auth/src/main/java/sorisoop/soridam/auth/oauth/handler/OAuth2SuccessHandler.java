package sorisoop.soridam.auth.oauth.handler;

import static sorisoop.soridam.domain.user.domain.Role.NOT_REGISTERED;

import java.time.Duration;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.jwt.JwtProvider;
import sorisoop.soridam.auth.oauth.UserPrincipal;
import sorisoop.soridam.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final JwtProvider jwtProvider;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userPrincipal.getUser();

		if (user.getRole().equals(NOT_REGISTERED)) {
			String refreshToken = jwtProvider.generateToken(user.getId(), user.getRole(), Duration.ofDays(7));
			String accessToken = jwtProvider.generateToken(user.getId(), user.getRole(), Duration.ofHours(2));

			response.addHeader("Authorization", "Bearer " + accessToken);
		} else {
			logger.info("TODO: 추가 정보 입력 페이지로 리다이렉트");
			// TODO: 추가 정보 입력 페이지로 리다이렉트
			// String redirectUrl = UriComponentsBuilder.fromUriString("/additional-info")
			// 	.queryParam("identifier", userIdentifier)
			// 	.build()
			// 	.toUriString();
			//
			// getRedirectStrategy().sendRedirect(request, response, redirectUrl);
		}
	}
}
