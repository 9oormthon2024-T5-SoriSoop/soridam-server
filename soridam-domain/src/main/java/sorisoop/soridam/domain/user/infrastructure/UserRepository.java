package sorisoop.soridam.domain.user.infrastructure;

import java.util.Optional;

import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;

public interface UserRepository {
	User save(User user);

	Optional<User> findByEmail(String email);

	Optional<User> findByOauthIdentityAndProvider(String oauthIdentifier, Provider provider);

	Optional<User> findById(String id);

	Optional<User> findUserDocumentById(String id);

	void flush();
}
