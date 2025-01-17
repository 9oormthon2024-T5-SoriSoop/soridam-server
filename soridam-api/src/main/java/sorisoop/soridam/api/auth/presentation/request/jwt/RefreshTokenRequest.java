package sorisoop.soridam.api.auth.presentation.request.jwt;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
	@Schema(description = "refresh token",
		example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsILJdhDYTYzNzQwNjQwMH0.7J",
		requiredMode = REQUIRED)
	@NotNull
	String refreshToken
) {
}
