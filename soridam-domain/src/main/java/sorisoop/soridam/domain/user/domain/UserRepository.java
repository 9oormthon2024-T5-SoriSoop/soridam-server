package sorisoop.soridam.domain.user.domain;

import java.util.Optional;

import sorisoop.soridam.domain.common.Provider;

public interface UserRepository {
	User save(User user);

	Optional<User> findByEmail(String email);

	Optional<User> findByOauthIdentityAndProvider(String oauthIdentifier, Provider provider);

	Optional<User> findById(Long id);
}
