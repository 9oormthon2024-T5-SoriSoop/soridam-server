package sorisoop.soridam.auth.oauth.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record OidcLoginRequest(
	@Schema(description = "id_token", example = "alswns11346@kgu.ac.kr", requiredMode = REQUIRED)
	@NotBlank
	String idToken
) {
}
