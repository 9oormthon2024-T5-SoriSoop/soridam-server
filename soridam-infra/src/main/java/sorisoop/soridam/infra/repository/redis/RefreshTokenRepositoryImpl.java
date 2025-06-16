package sorisoop.soridam.infra.repository.redis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.refresh.domain.RefreshToken;
import sorisoop.soridam.domain.user.refresh.domain.RefreshTokenRepository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
	private final RedisRefreshTokenRepository redisRefreshTokenRepository;

	@Override
	public Optional<RefreshToken> findByRefreshToken(String token) {
		return redisRefreshTokenRepository.findByRefreshToken(token);
	}

	@Override
	public void save(RefreshToken refreshToken) {
		redisRefreshTokenRepository.save(refreshToken);
	}


	@Override
	public boolean existById(String userId) {
		return redisRefreshTokenRepository.existsById(userId);
	}

	@Override
	public void deleteById(String userId) {
		redisRefreshTokenRepository.deleteById(userId);
	}
}
