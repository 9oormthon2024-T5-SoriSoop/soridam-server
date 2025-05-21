package sorisoop.soridam.domain.notification_service.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.notification_service.domain.Notification;
import sorisoop.soridam.domain.notification_service.domain.NotificationRepository;
import sorisoop.soridam.domain.notification_service.exception.NotificationNotFoundException;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {
	private final NotificationRepository notificationRepository;

	public List<Notification> findByReceiverIdWithCursor(Long receiverId, Long lastId, int limit) {
		return notificationRepository.findByReceiverIdWithCursor(receiverId, lastId, limit);
	}

	public Notification getById(Long id) {
		return notificationRepository.findById(id)
			.orElseThrow(NotificationNotFoundException::new);
	}
}
