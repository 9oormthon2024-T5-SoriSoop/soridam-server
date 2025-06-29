package sorisoop.soridam.infra.repository.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import sorisoop.soridam.domain.activitylog.domain.ActivityLog;

public interface DocumentActivityLogRepository extends ElasticsearchRepository<ActivityLog, String> {
}
