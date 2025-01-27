package sorisoop.soridam.domain.user.query.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QueryUserRepository extends MongoRepository<UserDocument, String> {
}
