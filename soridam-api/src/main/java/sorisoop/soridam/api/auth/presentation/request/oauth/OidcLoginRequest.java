package sorisoop.soridam.api.auth.presentation.request.oauth;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record OidcLoginRequest(
	@NotBlank
	@Schema(description = "id_token", example = "ID_TOKEN_EXAMPLE", requiredMode = REQUIRED)
	String idToken
) {
}
