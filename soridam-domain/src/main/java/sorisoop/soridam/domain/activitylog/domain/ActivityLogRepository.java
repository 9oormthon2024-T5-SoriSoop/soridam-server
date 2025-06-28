package sorisoop.soridam.domain.activitylog.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository {
	void save(ActivityLog activityLog);
}
