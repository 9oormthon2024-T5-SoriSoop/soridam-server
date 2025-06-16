package sorisoop.soridam.domain.notification.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.domain.notification.domain.NotificationRepository;

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
