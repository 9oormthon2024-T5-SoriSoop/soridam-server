package sorisoop.soridam.api.user.presentation.response;

import lombok.Builder;
import sorisoop.soridam.domain.user.domain.User;

@Builder
public record UserPersistResponse(
	String userId
) {
	public static UserPersistResponse from(User user) {
		return UserPersistResponse.builder()
			.userId(user.extractUuid())
			.build();
	}
}
