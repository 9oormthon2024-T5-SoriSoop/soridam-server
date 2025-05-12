package sorisoop.soridam.domain.notification.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.domain.notification.domain.NotificationRepository;
import sorisoop.soridam.domain.notification.exception.NotificationNotFoundException;

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
