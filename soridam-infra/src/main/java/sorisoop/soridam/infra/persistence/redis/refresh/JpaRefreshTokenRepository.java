package sorisoop.soridam.infra.persistence.redis.refresh;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface JpaRefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
	Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);
}
