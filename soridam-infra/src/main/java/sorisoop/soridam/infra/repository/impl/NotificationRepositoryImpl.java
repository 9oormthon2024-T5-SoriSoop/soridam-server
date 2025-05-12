package sorisoop.soridam.infra.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.notification.Notification;
import sorisoop.soridam.domain.notification.NotificationRepository;
import sorisoop.soridam.infra.repository.jpa.JpaNotificationRepository;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {
	private final JpaNotificationRepository jpaNotificationRepository;

	@Override
	public List<Notification> findByReceiverIdOrderByCreatedAtDesc(Long receiverId) {
		return jpaNotificationRepository.findByReceiverIdOrderByCreatedAtDesc(receiverId);
	}

	@Override
	public void saveAll(List<Notification> notifications) {
		jpaNotificationRepository.saveAll(notifications);
	}
}
