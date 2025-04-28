package sorisoop.soridam.api.user.presentation.response;

import lombok.Builder;
import sorisoop.soridam.domain.user.domain.User;

@Builder
public record UserPersistResponse(
	Long id
) {
	public static UserPersistResponse from(User user) {
		return UserPersistResponse.builder()
			.id(user.getId())
			.build();
	}
}
