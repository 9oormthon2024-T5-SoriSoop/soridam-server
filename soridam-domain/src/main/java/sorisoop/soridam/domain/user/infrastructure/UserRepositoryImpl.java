package sorisoop.soridam.domain.user.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.common.domain.Provider;
import sorisoop.soridam.domain.user.command.domain.JpaUserRepository;
import sorisoop.soridam.domain.user.command.domain.User;
import sorisoop.soridam.domain.user.query.domain.QueryUserRepository;
import sorisoop.soridam.domain.user.query.domain.UserDocument;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final JpaUserRepository jpaUserRepository;
	private final QueryUserRepository queryUserRepository;

	@Override
	public User save(User user) {
		return jpaUserRepository.save(user);
	}

	@Override
	public Optional<UserDocument> findByEmail(String email) {
		return queryUserRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByOauthIdentityAndProvider(String oauthIdentifier, Provider provider) {
		return jpaUserRepository.findByOauthIdentityAndProvider(oauthIdentifier, provider);
	}

	@Override
	public Optional<UserDocument> findById(String id) {
		return queryUserRepository.findById(id);
	}
}
