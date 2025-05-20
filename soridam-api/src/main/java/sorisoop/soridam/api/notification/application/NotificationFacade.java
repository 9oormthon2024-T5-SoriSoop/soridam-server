package sorisoop.soridam.api.notification.application;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.notification.presentation.response.NotificationResponse;
import sorisoop.soridam.common.response.SliceResponse;
import sorisoop.soridam.domain.notification.application.NotificationCommandService;
import sorisoop.soridam.domain.notification.application.NotificationQueryService;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.infra.notification.sse.SseEmitterManager;

@Component
@RequiredArgsConstructor
public class NotificationFacade {
	private final UserQueryService userQueryService;
	private final NotificationQueryService notificationQueryService;
	private final NotificationCommandService notificationCommandService;
	private final SseEmitterManager sseEmitterManager;

	@Transactional(readOnly = true)
	public SliceResponse<NotificationResponse> getMyNotifications(Long lastId, int limit) {
		User receiver = userQueryService.me();
		List<Notification> notifications = notificationQueryService.findByReceiverIdWithCursor(
			receiver.getId(), lastId, limit + 1
		);

		boolean hasNext = notifications.size() > limit;
		if (hasNext) notifications = notifications.subList(0, limit);

		List<NotificationResponse> responses = notifications.stream()
			.map(NotificationResponse::from)
			.toList();

		String newLastCursor = responses.isEmpty() ? null : String.valueOf(responses.get(responses.size() - 1).id());

		return SliceResponse.of(responses, newLastCursor, hasNext);
	}

	@Transactional
	public void markAsRead(Long id) {
		Notification notification = notificationQueryService.getById(id);
		notificationCommandService.markAsRead(notification);
	}

	@Transactional
	public SseEmitter sseConnect() {
		User receiver = userQueryService.me();
		return sseEmitterManager.connect(receiver.getId());
	}
}
