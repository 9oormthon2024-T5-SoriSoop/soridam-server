package sorisoop.soridam.domain.user.infrastructure;

import java.util.Optional;

import sorisoop.soridam.common.domain.Provider;
import sorisoop.soridam.domain.user.command.domain.User;
import sorisoop.soridam.domain.user.query.domain.UserDocument;

public interface UserRepository {
	User save(User user);

	Optional<UserDocument> findByEmail(String email);

	Optional<User> findByOauthIdentityAndProvider(String oauthIdentifier, Provider provider);

	Optional<UserDocument> findById(String id);
}
