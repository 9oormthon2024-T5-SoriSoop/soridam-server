package sorisoop.soridam.api.user.presentation.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
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
	List<NoiseSummaryResponse> noises
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
				.map(NoiseSummaryResponse::from)
				.toList())
			.build();
	}
}
