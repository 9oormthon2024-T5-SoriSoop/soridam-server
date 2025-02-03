package sorisoop.soridam.infra.persistence.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.refresh.RefreshToken;
import sorisoop.soridam.domain.refresh.RefreshTokenRepository;
import sorisoop.soridam.infra.persistence.redis.refresh.JpaRefreshTokenRepository;
import sorisoop.soridam.infra.persistence.redis.refresh.RefreshTokenEntity;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
	private final JpaRefreshTokenRepository jpaRefreshTokenRepository;

	@Override
	public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
		return jpaRefreshTokenRepository.findByRefreshToken(refreshToken)
			.map(RefreshTokenEntity::of);
	}

	@Override
	public Optional<RefreshToken> findById(String userId) {
		return jpaRefreshTokenRepository.findById(userId)
			.map(RefreshTokenEntity::of);
	}

	@Override
	public void delete(RefreshToken refreshToken) {
		jpaRefreshTokenRepository.delete(RefreshTokenEntity.from(refreshToken));
	}

	@Override
	public void save(RefreshToken refreshToken) {
		jpaRefreshTokenRepository.save(RefreshTokenEntity.from(refreshToken));
	}
}
