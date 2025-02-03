package sorisoop.soridam.domain.refresh;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository {
	Optional<RefreshToken> findByRefreshToken(String refreshToken);

	Optional<RefreshToken> findById(String userId);

	void delete(RefreshToken refreshToken);

	void save(RefreshToken refreshToken);
}
