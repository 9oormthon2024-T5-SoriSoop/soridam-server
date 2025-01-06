package sorisoop.soridam.api.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.auth.application.AuthService;
import sorisoop.soridam.api.auth.presentation.request.jwt.JwtLoginRequest;
import sorisoop.soridam.api.auth.presentation.response.jwt.JwtResponse;
import sorisoop.soridam.auth.oauth.request.OidcLoginRequest;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "로그인 API")
@RequestMapping("/api/auth")
public class AuthApiController {
	private final AuthService authService;

	@Operation(summary = "JWT 로그인 API", description = """
			- Description : 이 API는 로그인 시 JWT를 발급합니다.
		""")
	@ApiResponse(responseCode = "200")
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(
		@Valid
		@RequestBody
		JwtLoginRequest request
	) {
		JwtResponse response = authService.jwtLogin(request);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/oauth2/kakao")
	public ResponseEntity<JwtResponse> kakaoSocialLogin(
		@Valid
		@RequestBody
		OidcLoginRequest idToken
	){
		JwtResponse response = authService.kakaoLogin(idToken);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/oauth2/google")
	public ResponseEntity<JwtResponse> googleSocialLogin(
		@Valid
		@RequestBody
		OidcLoginRequest idToken
	){
		JwtResponse response = authService.googleLogin(idToken);
		return ResponseEntity.ok(response);
	}
}
