package sorisoop.soridam.infra.config.data.redis.event;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewEventPublisher {
	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper;

	private static final String STREAM_KEY = "stream:review_created";

	public void publishReviewEvent(ReviewCreatedEvent event) {
		try {
			String json = objectMapper.writeValueAsString(event);
			redisTemplate.opsForStream().add(STREAM_KEY, Map.of("event", json));
		} catch (JsonProcessingException e) {
			throw new RuntimeException("리뷰 이벤트 직렬화 실패", e);
		}
	}
}
