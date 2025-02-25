package sorisoop.soridam.infra.repository.redis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.refresh.domain.RefreshToken;
import sorisoop.soridam.domain.refresh.domain.RefreshTokenRepository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
	private final RedisRefreshTokenRepository redisRefreshTokenRepository;

	@Override
	public Optional<RefreshToken> findByRefreshToken(String token) {
		return redisRefreshTokenRepository.findByRefreshToken(token);
	}

	@Override
	public Optional<RefreshToken> findById(String id) {
		return redisRefreshTokenRepository.findById(id);
	}

	@Override
	public void delete(RefreshToken refreshToken) {
		redisRefreshTokenRepository.delete(refreshToken);
	}

	@Override
	public void save(RefreshToken refreshToken) {
		redisRefreshTokenRepository.save(refreshToken);
	}
}
