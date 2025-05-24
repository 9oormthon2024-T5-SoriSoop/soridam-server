package sorisoop.soridam.infra.repository.notification_service.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.notification_service.domain.Notification;

public interface JpaNotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByReceiverIdOrderByCreatedAtDesc(Long receiverId);
}
