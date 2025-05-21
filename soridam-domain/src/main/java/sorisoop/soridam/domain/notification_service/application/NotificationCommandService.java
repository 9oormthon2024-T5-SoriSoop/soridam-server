package sorisoop.soridam.domain.notification_service.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.notification_service.domain.Notification;
import sorisoop.soridam.domain.notification_service.domain.NotificationRepository;

@Service
@RequiredArgsConstructor
public class NotificationCommandService {
	private final NotificationRepository notificationRepository;

	public void createNotifications(List<Notification> notifications) {
		notificationRepository.saveAll(notifications);
	}

	public void markAsRead(Notification notification) {
		notification.markAsRead();
	}
}
