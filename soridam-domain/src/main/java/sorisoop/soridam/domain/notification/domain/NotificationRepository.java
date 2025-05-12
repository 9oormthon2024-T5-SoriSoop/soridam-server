package sorisoop.soridam.domain.notification.domain;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
	List<Notification> findByReceiverIdWithCursor(Long receiverId, Long lastId, int limit);

	void saveAll(List<Notification> notification);

	Optional<Notification> findById(Long id);
}
