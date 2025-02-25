package sorisoop.soridam.domain.refresh.domain;

import java.util.Optional;

public interface RefreshTokenRepository {
	Optional<RefreshToken> findByRefreshToken(String token);

	Optional<RefreshToken> findById(String userId);

	void delete(RefreshToken refreshToken);

	void save(RefreshToken refreshToken);
}
