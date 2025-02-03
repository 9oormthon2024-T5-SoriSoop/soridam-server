package sorisoop.soridam.infra.persistence.redis.refresh;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import sorisoop.soridam.domain.refresh.RefreshToken;

@RedisHash(value = "refreshToken", timeToLive = 86400)
@AllArgsConstructor
public class RefreshTokenEntity {
	@Id
	private String userId;

	@Indexed
	private String token;

	public RefreshToken of() {
		return RefreshToken.of(userId, token);
	}

	public static RefreshTokenEntity from(RefreshToken refreshToken) {
		return new RefreshTokenEntity(refreshToken.getUserId(), refreshToken.getRefreshToken());
	}
}
