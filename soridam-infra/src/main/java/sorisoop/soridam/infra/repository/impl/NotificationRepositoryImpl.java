package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.notification.domain.Notification;
import sorisoop.soridam.domain.notification.domain.NotificationRepository;
import sorisoop.soridam.infra.repository.jpa.JpaNotificationRepository;
import sorisoop.soridam.infra.repository.query.QueryNotificationRepository;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {
	private final JpaNotificationRepository jpaNotificationRepository;
	private final QueryNotificationRepository queryNotificationRepository;

	@Override
	public List<Notification> findByReceiverIdWithCursor(Long receiverId, Long lastId, int limit) {
		return queryNotificationRepository.findByReceiverIdWithCursor(receiverId, lastId, limit);
	}

	@Override
	public void saveAll(List<Notification> notifications) {
		jpaNotificationRepository.saveAll(notifications);
	}

	@Override
	public Optional<Notification> findById(Long id) {
		return jpaNotificationRepository.findById(id);
	}
}
