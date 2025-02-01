package sorisoop.soridam.domain.user.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.user.query.domain.QueryUserRepository;
import sorisoop.soridam.domain.user.query.domain.UserDocument;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventConsumer {
	private final QueryUserRepository queryUserRepository;
	private final ObjectMapper objectMapper;

	@KafkaListener(topics = "soridam.dbserver1.public.user", groupId = "user-events-group")
	public void consumeUserEvent(String message) {
		log.info("Received user event: {}", message);

		try {
			JsonNode rootNode = objectMapper.readTree(message);
			String operation = rootNode.get("op").asText();
			JsonNode after = rootNode.get("after");
			JsonNode before = rootNode.get("before");

			switch (operation) {
				case "c":
					UserDocument createdUser = objectMapper.treeToValue(after, UserDocument.class);
					queryUserRepository.save(createdUser);
					log.info("Processed CREATE event: {}", createdUser);
					break;

				case "u":
					UserDocument updatedUser = objectMapper.treeToValue(after, UserDocument.class);
					queryUserRepository.save(updatedUser);
					log.info("Processed UPDATE event: {}", updatedUser);
					break;

				case "d":
					String deletedId = before.get("id").asText();
					queryUserRepository.deleteById(deletedId);
					log.info("Processed DELETE event for ID: {}", deletedId);
					break;

				default:
					log.warn("Unhandled operation type: {}", operation);
					break;
			}
		} catch (Exception e) {
			log.error("Failed to process user event: {}", message, e);
		}
	}
}
