package sorisoop.soridam.domain.user.refresh.domain;

import java.util.Optional;

public interface RefreshTokenRepository {
	Optional<RefreshToken> findByRefreshToken(String token);

	void save(RefreshToken refreshToken);

	boolean existById(String userId);

	void deleteById(String userId);
}
