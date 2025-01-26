package sorisoop.soridam.domain.user.application;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class UserProducer {
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(User user) {}
}
