package sorisoop.soridam.domain.user.query.domain;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QueryUserRepository extends MongoRepository<UserDocument, String> {
	Optional<UserDocument> findByEmail(String email);
}
