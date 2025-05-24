package sorisoop.soridam.infra.repository.user_service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sorisoop.soridam.domain.user_service.refresh.domain.RefreshToken;

@Repository
public interface RedisRefreshTokenRepository extends CrudRepository<RefreshToken, String> {
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
