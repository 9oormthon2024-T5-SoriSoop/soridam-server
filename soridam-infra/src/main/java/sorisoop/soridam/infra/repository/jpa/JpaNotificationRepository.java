package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.notification.domain.Notification;

public interface JpaNotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByReceiverIdOrderByCreatedAtDesc(Long receiverId);
}
