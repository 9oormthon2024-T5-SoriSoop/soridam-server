package sorisoop.soridam.api.auth.presentation.request.jwt;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record JwtLoginRequest(
	@Schema(description = "이메일", example = "alswns11346@kgu.ac.kr", requiredMode = REQUIRED)
	@NotBlank
	String userId,

	@Schema(description = "비밀번호", example = "password1234!", requiredMode = REQUIRED)
	@NotBlank
	String password
) {
}
