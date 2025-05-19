package sorisoop.soridam.infra.notification;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class SseEmitterManager {
	private static final Long TIMEOUT = 60 * 60 * 1000L;
	private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

	public SseEmitter connect(Long userId) {
		SseEmitter emitter = new SseEmitter(TIMEOUT);
		emitters.put(userId, emitter);

		emitter.onCompletion(() -> emitters.remove(userId));
		emitter.onTimeout(() -> emitters.remove(userId));
		emitter.onError(e -> emitters.remove(userId));

		return emitter;
	}

	public void sendToUser(Long userId, String content) {
		SseEmitter emitter = emitters.get(userId);
		if (emitter != null) {
			try {
				emitter.send(SseEmitter.event()
					.data(content));
			} catch (Exception e) {
				emitter.completeWithError(e);
			}
		}
	}
}
