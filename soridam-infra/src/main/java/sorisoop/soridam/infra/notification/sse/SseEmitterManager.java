package sorisoop.soridam.infra.notification.sse;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.infra.notification.sse.exception.SseConnectionFailedException;

@Slf4j
@Component
public class SseEmitterManager {
	private static final Long TIMEOUT = 60 * 60 * 1000L;

	private final ConcurrentHashMap<Long, CopyOnWriteArraySet<SseEmitter>> userEmitters = new ConcurrentHashMap<>();

	public SseEmitter connect(Long userId) {
		try {
			SseEmitter emitter = new SseEmitter(TIMEOUT);
			userEmitters.computeIfAbsent(userId, k -> new CopyOnWriteArraySet<>()).add(emitter);

			emitter.onCompletion(() -> removeEmitter(userId, emitter));
			emitter.onTimeout(() -> removeEmitter(userId, emitter));
			emitter.onError((e) -> {
				log.warn("SSE emitter 오류 발생: userId={}, error={}", userId, e.getMessage());
				removeEmitter(userId, emitter);
			});

			return emitter;
		} catch (Exception e) {
			log.error("SSE 연결 중 예외 발생: userId={}, error={}", userId, e.getMessage(), e);
			throw new SseConnectionFailedException();
		}
	}


	private void removeEmitter(Long userId, SseEmitter emitter) {
		Set<SseEmitter> emitters = userEmitters.get(userId);
		if (emitters != null) {
			emitters.remove(emitter);
			if (emitters.isEmpty()) {
				userEmitters.remove(userId);
			}
		}
	}

	public void sendToUser(Long userId, Object payload) {
		Set<SseEmitter> emitters = userEmitters.get(userId);
		if (emitters == null || emitters.isEmpty()) return;

		Set<SseEmitter> deadEmitters = new CopyOnWriteArraySet<>();

		for (SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event()
					.name("review-notification")
					.data(payload));
			} catch (IOException e) {
				log.warn("SSE 전송 실패: userId={}, emitterHash={}", userId, emitter.hashCode());
				deadEmitters.add(emitter);
				emitter.completeWithError(e);
			}
		}

		if (!deadEmitters.isEmpty()) {
			emitters.removeAll(deadEmitters);
			if (emitters.isEmpty()) {
				userEmitters.remove(userId);
			}
		}
	}
}
