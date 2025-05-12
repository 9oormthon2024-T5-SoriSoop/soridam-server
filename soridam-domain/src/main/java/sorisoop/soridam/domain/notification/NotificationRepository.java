package sorisoop.soridam.domain.notification;

import java.util.List;

public interface NotificationRepository {
	List<Notification> findByReceiverIdOrderByCreatedAtDesc(Long receiverId);

	void saveAll(List<Notification> notification);
}
