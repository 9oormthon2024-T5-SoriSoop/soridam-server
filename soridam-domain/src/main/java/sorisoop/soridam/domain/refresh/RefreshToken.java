package sorisoop.soridam.domain.refresh;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RefreshToken {
	private String userId;

	private String refreshToken;

	public static RefreshToken of(String userId, String refreshToken) {
		return RefreshToken.builder()
			.userId(userId)
			.refreshToken(refreshToken)
			.build();
	}
}
