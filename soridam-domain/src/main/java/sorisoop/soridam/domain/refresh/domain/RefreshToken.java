package sorisoop.soridam.domain.refresh.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24)
@Builder
@AllArgsConstructor
public class RefreshToken {
	@Id
	private Long userId;

	@Indexed
	private String refreshToken;

	public static RefreshToken of(Long userId, String refreshToken) {
		return RefreshToken.builder()
			.userId(userId)
			.refreshToken(refreshToken)
			.build();
	}
}
