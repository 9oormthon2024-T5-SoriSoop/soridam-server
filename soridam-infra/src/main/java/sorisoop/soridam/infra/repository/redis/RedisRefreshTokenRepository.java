package sorisoop.soridam.infra.repository.redis;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sorisoop.soridam.domain.user.refresh.domain.RefreshToken;

@Repository
public interface RedisRefreshTokenRepository extends CrudRepository<RefreshToken, String> {
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
