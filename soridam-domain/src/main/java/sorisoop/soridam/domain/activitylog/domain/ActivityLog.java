package sorisoop.soridam.domain.activitylog.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import lombok.Builder;
import lombok.Getter;
import sorisoop.soridam.domain.activitylog.domain.enums.ActivityType;

@Getter
@Builder
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

	@Field(type = FieldType.Double)
	private final Double customScore;

	@Field(type = FieldType.Date)
	private final LocalDateTime createdAt;

	public Double getScore() {
		return customScore != null ? customScore : activityType.getScore();
	}

	public static ActivityLog create(Long userId, Long placeId, double lat, double lon,
		ActivityType activityType, Double customScore) {
		return ActivityLog.builder()
			.userId(userId)
			.placeId(placeId)
			.latlon(new GeoPoint(lat, lon))
			.activityType(activityType)
			.customScore(customScore)
			.createdAt(LocalDateTime.now())
			.build();
	}
}
