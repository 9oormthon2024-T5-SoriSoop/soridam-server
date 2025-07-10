package sorisoop.soridam.domain.activitylog.domain;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.activitylog.domain.enums.ActivityType;
import sorisoop.soridam.domain.review.domain.ReviewTag;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED, force = true)
@AllArgsConstructor(access = PROTECTED)
@Document(indexName = "activity_log-#{T(java.time.LocalDate).now().format(T(java.time.format.DateTimeFormatter).ofPattern('yyyy-MM'))}")
public class ActivityLog {
	@Id
	private String id;

	@Field(type = FieldType.Long)
	private final Long userId;

	@Field(type = FieldType.Long)
	private final Long placeId;

	private final GeoPoint latlon;

	@Field(type = FieldType.Keyword)
	private final ActivityType activityType;

	@Field(type = FieldType.Date)
	private final LocalDateTime createdAt;

	@Field(type = FieldType.Keyword)
	private final Set<ReviewTag> reviewTag;

	public Double getScore() {
		return activityType.getScore();
	}

	public static ActivityLog create(Long userId, Long placeId, double lat, double lon,
		ActivityType activityType) {
		return ActivityLog.builder()
			.userId(userId)
			.placeId(placeId)
			.latlon(new GeoPoint(lat, lon))
			.activityType(activityType)
			.createdAt(LocalDateTime.now())
			.build();
	}

	public static ActivityLog create(Long userId, Long placeId, double lat, double lon,
		ActivityType activityType, Set<ReviewTag> reviewTag) {
		return ActivityLog.builder()
			.userId(userId)
			.placeId(placeId)
			.latlon(new GeoPoint(lat, lon))
			.activityType(activityType)
			.createdAt(LocalDateTime.now())
			.reviewTag(reviewTag)
			.build();
	}
}
