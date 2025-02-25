package sorisoop.soridam.infra.config.data.replication;

import static sorisoop.soridam.infra.config.data.replication.DataSourceType.MASTER;
import static sorisoop.soridam.infra.config.data.replication.DataSourceType.SLAVE;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? SLAVE :
			MASTER;
	}
}
