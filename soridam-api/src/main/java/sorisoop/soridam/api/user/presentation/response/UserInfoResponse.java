package sorisoop.soridam.api.user.presentation.response;

import java.time.LocalDateTime;

import lombok.Builder;
import sorisoop.soridam.domain.user.domain.User;

@Builder
public record UserInfoResponse(
	String id,
	String email,
	String name,
	String nickname,
	LocalDateTime lastLoginAt
) {
	public static UserInfoResponse from(User user) {
		return UserInfoResponse.builder()
			.id(user.getId())
			.email(user.getEmail())
			.name(user.getName())
			.nickname(user.getNickname())
			.lastLoginAt(user.getLastLoginAt())
			.build();
	}
}
