package sorisoop.soridam.domain.user.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.common.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User, String> {
	Optional<User> findByEmail(String email);

	Optional<User> findByOauthIdentityAndProvider(String oauthIdentifier, Provider provider);
}
