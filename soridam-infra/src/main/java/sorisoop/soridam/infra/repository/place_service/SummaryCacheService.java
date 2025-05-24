package sorisoop.soridam.infra.repository.place_service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SummaryCacheService {
	private final RedisTemplate<String, String> redisTemplate;

	private static final String KEY_PREFIX = "summary:";

	public void save(Long addressId, String summary) {
		String key = generateKey(addressId);
		redisTemplate.opsForValue().set(key, summary);
	}

	public String get(Long addressId) {
		String key = generateKey(addressId);
		return redisTemplate.opsForValue().get(key);
	}

	private String generateKey(Long addressId) {
		return KEY_PREFIX + addressId;
	}
}
