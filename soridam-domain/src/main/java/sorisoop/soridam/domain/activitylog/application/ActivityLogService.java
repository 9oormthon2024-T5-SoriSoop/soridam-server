package sorisoop.soridam.domain.activitylog.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.activitylog.domain.ActivityLog;
import sorisoop.soridam.domain.activitylog.domain.ActivityLogRepository;

@Service
@RequiredArgsConstructor
public class ActivityLogService {
	private final ActivityLogRepository activityLogRepository;

	public void saveActivityLog(ActivityLog activityLog) {
		activityLogRepository.save(activityLog);
	}
}
