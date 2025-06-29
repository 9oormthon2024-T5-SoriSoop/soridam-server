package sorisoop.soridam.domain.activitylog.application;

import org.locationtech.jts.geom.Point;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.activitylog.domain.ActivityLog;
import sorisoop.soridam.domain.activitylog.domain.ActivityLogRepository;
import sorisoop.soridam.domain.activitylog.domain.enums.ActivityType;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.user.user.domain.User;

@Service
@RequiredArgsConstructor
public class ActivityLogService {
	private final ActivityLogRepository activityLogRepository;

	@Async("activityLogExecutor")
	public void save(User user, Place place, ActivityType activityType) {
		if (user == null || place == null || activityType == null) return;

		Point location = place.getLocation();

		if (location == null) return;

		double lon = location.getX();
		double lat = location.getY();

		ActivityLog activityLog = ActivityLog.create(
			user.getId(),
			place.getId(),
			lat,
			lon,
			activityType,
			activityType.getScore()
		);

		activityLogRepository.save(activityLog);
	}
}
