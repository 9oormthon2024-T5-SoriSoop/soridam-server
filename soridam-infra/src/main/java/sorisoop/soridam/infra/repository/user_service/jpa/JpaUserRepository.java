package sorisoop.soridam.infra.repository.user_service.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.common.Provider;
import sorisoop.soridam.domain.user_service.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByOauthIdentityAndProvider(String oauthIdentifier, Provider provider);
}
