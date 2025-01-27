package sorisoop.soridam.domain.user.infrastructure;

import java.util.Optional;

import sorisoop.soridam.common.domain.Provider;
import sorisoop.soridam.domain.user.command.domain.User;
import sorisoop.soridam.domain.user.query.domain.UserDocument;

public interface UserRepository {
	User save(User user);

	Optional<User> findByEmail(String email);

	Optional<User> findByOauthIdentityAndProvider(String oauthIdentifier, Provider provider);

	Optional<User> findById(String id);

	Optional<UserDocument> findUserDocumentById(String id);
}
