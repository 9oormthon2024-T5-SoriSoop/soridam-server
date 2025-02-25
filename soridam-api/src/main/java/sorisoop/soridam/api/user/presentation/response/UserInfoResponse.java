package sorisoop.soridam.api.user.presentation.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import sorisoop.soridam.api.noise.presentation.response.NoiseResponse;
import sorisoop.soridam.domain.user.domain.User;

@Builder
public record UserInfoResponse(
	String id,
	String email,
	String name,
	String nickname,
	LocalDate birthDate,
	String phoneNumber,
	String profileImageUrl,
	int point,
	LocalDateTime lastLoginAt,
	List<NoiseResponse> noises
) {
	public static UserInfoResponse from(User user) {
		return UserInfoResponse.builder()
			.id(user.getId())
			.email(user.getEmail())
			.name(user.getName())
			.nickname(user.getNickname())
			.birthDate(user.getBirthDate())
			.phoneNumber(user.getPhoneNumber())
			.profileImageUrl(user.getProfileImageUrl())
			.point(user.getPoint())
			.lastLoginAt(user.getLastLoginAt())
			.noises(user.getNoises().stream()
				.map(NoiseResponse::from)
				.toList())
			.build();
	}
}
