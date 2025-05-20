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
	private static final String EVENT_NAME = "review-notification";

	private final ConcurrentHashMap<Long, CopyOnWriteArraySet<SseEmitter>> userEmitters = new ConcurrentHashMap<>();

	public SseEmitter connect(Long userId) {
		try {
			CopyOnWriteArraySet<SseEmitter> userEmitterSet = userEmitters.computeIfAbsent(userId, k -> new CopyOnWriteArraySet<>());

			if (userEmitterSet.size() >= 10) {
				log.warn("Emitter 수 초과: userId={}, 제거 전 count={}", userId, userEmitterSet.size());
				SseEmitter oldest = userEmitterSet.iterator().next();
				oldest.complete();
				userEmitterSet.remove(oldest);
			}

			SseEmitter emitter = new SseEmitter(TIMEOUT);
			userEmitterSet.add(emitter);

			emitter.onCompletion(() -> removeEmitter(userId, emitter));
			emitter.onTimeout(() -> removeEmitter(userId, emitter));
			emitter.onError((e) -> {
				log.warn("SSE emitter 오류 발생: userId={}, error={}", userId, e.getMessage());
				removeEmitter(userId, emitter);
			});

			log.info("SSE 연결 완료: userId={}, emitterHash={}", userId, emitter.hashCode());
			return emitter;

		} catch (Exception e) {
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
					.name(EVENT_NAME)
					.data(payload));
			} catch (IOException e) {
				log.warn("SSE 전송 실패: userId={}, emitterHash={}, error={}", userId, emitter.hashCode(), e.getMessage());
				deadEmitters.add(emitter);
				emitter.completeWithError(e);
			}
		}

		if (!deadEmitters.isEmpty()) {
			log.info("제거된 죽은 Emitter 수: {}, userId={}", deadEmitters.size(), userId);
			emitters.removeAll(deadEmitters);
			if (emitters.isEmpty()) {
				userEmitters.remove(userId);
			}
		}
	}
}
