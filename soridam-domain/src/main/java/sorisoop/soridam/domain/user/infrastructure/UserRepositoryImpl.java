package sorisoop.soridam.domain.user.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.common.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.domain.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final JpaUserRepository jpaUserRepository;

	@Override
	public User save(User user) {
		return jpaUserRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return jpaUserRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByOauthIdentityAndProvider(String oauthIdentifier, Provider provider) {
		return jpaUserRepository.findByOauthIdentityAndProvider(oauthIdentifier, provider);
	}

	@Override
	public Optional<User> findById(String id) {
		return jpaUserRepository.findById(id);
	}
}
