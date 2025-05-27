package sorisoop.soridam.infra.repository.query;

import static sorisoop.soridam.domain.notification.domain.QNotification.notification;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.notification.domain.Notification;

@Repository
@RequiredArgsConstructor
public class QueryNotificationRepository {
	private final JPAQueryFactory jpaQueryFactory;

	public List<Notification> findByReceiverIdWithCursor(Long receiverId, Long lastId, int limit) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(notification.receiverId.eq(receiverId));
		if (lastId != null) {
			builder.and(notification.id.lt(lastId));
		}

		return jpaQueryFactory.selectFrom(notification)
			.where(builder)
			.orderBy(notification.id.desc())
			.limit(limit)
			.fetch();
	}
}
