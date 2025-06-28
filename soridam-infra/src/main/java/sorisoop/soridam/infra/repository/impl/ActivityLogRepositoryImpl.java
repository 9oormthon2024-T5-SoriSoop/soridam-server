package sorisoop.soridam.infra.repository.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.activitylog.domain.ActivityLog;
import sorisoop.soridam.domain.activitylog.domain.ActivityLogRepository;
import sorisoop.soridam.infra.repository.es.DocumentActivityLogRepository;

@Repository
@RequiredArgsConstructor
public class ActivityLogRepositoryImpl implements ActivityLogRepository {
	private final DocumentActivityLogRepository documentActivityLogRepository;

	@Override
	public void save(ActivityLog activityLog) {
		documentActivityLogRepository.save(activityLog);
	}
}
