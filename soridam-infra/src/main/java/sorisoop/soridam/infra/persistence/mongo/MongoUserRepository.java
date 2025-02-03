package sorisoop.soridam.infra.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import sorisoop.soridam.infra.persistence.mongo.document.UserDocument;

public interface MongoUserRepository extends MongoRepository<UserDocument, String> {
}
