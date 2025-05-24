package sorisoop.soridam.domain.notification_service.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.domain.notification_service.domain.enums.NotificationType.REVIEW;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.common.BaseTimeEntity;
import sorisoop.soridam.domain.notification_service.domain.enums.NotificationType;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
public class Notification extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private Long receiverId;

	private Long placeId;

	@Enumerated(value = STRING)
	private NotificationType type;

	private String content;

	private boolean isRead;

	public void markAsRead() {
		this.isRead = true;
	}

	public static Notification createReviewNotification(Long receiverId, Long placeId, String content) {
		return Notification.builder()
			.receiverId(receiverId)
			.placeId(placeId)
			.type(REVIEW)
			.content(content)
			.isRead(false)
			.build();
	}
}
