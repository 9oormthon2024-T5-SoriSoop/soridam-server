package sorisoop.soridam.api.user.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateRequest(
	@Schema(description = "이메일", example = "alswns11346@kgu.ac.kr", requiredMode = REQUIRED)
	@NotBlank
	String email,

	@Schema(description = "비밀번호", example = "password1234!", requiredMode = REQUIRED)
	@NotBlank
	String password,

	@Schema(description = "이름", example = "박민준", requiredMode = REQUIRED)
	@NotBlank
	String name,

	@Schema(description = "별명", example = "nninjo_on", requiredMode = REQUIRED)
	@NotBlank
	String nickname,

	@Schema(description = "생일", example = "1999-10-22", requiredMode = REQUIRED)
	@NotNull
	LocalDate birthDate,

	@Schema(description = "휴대폰 번호", example = "01012345678", requiredMode = REQUIRED)
	@NotBlank
	String phoneNumber,

	@Schema(description = "프로필 사진", example = "https://example.com/profile.jpg")
	String profileImageUrl
) {
}
