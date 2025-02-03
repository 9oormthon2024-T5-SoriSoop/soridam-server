package sorisoop.soridam.infra.persistence.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.UserRepository;
import sorisoop.soridam.infra.persistence.mongo.document.UserDocument;
import sorisoop.soridam.infra.persistence.jpa.JpaUserRepository;
import sorisoop.soridam.infra.persistence.mongo.MongoUserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final JpaUserRepository jpaUserRepository;
	private final MongoUserRepository mongoUserRepository;

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

	@Override
	public Optional<User> findUserDocumentById(String id) {
		return mongoUserRepository.findById(id)
			.map(UserDocument::toEntity);
	}

	@Override
	public void flush() {
		jpaUserRepository.flush();
	}
}
