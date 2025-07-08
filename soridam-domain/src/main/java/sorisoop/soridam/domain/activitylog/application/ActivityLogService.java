package sorisoop.soridam.domain.activitylog.application;

import java.util.Set;

import org.locationtech.jts.geom.Point;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.activitylog.domain.ActivityLog;
import sorisoop.soridam.domain.activitylog.domain.ActivityLogRepository;
import sorisoop.soridam.domain.activitylog.domain.enums.ActivityType;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.review.domain.ReviewTag;
import sorisoop.soridam.domain.user.user.domain.User;

@Service
@RequiredArgsConstructor
public class ActivityLogService {
	private final ActivityLogRepository activityLogRepository;

	@Async("activityLogExecutor")
	public void save(User user, Place place, ActivityType activityType) {
		if (validateAndLogInput(user, place, activityType)) return;

		double[] coords = extractCoordinates(place);
		double lon = coords[0];
		double lat = coords[1];

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

	@Async("activityLogExecutor")
	public void save(User user, Place place, ActivityType activityType, Set<ReviewTag> tags) {
		if (validateAndLogInput(user, place, activityType)) return;

		double[] coords = extractCoordinates(place);
		double lon = coords[0];
		double lat = coords[1];

		ActivityLog activityLog = ActivityLog.create(
				user.getId(),
				place.getId(),
				lat,
				lon,
				activityType,
				activityType.getScore(),
				tags
			);

		activityLogRepository.save(activityLog);
	}

	private boolean validateAndLogInput(User user, Place place, ActivityType activityType) {
		if (user == null || place == null || activityType == null) return true;
		return place.getLocation() == null;
	}

	private double[] extractCoordinates(Place place) {
		Point location = place.getLocation();
		return new double[]{location.getX(), location.getY()};
	}
}
