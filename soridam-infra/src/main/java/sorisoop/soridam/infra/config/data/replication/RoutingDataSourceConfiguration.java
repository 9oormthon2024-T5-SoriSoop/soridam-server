package sorisoop.soridam.infra.config.data.replication;

import static sorisoop.soridam.infra.config.data.replication.DataSourceConfiguration.MASTER_DATA_SOURCE;
import static sorisoop.soridam.infra.config.data.replication.DataSourceConfiguration.SLAVE_DATA_SOURCE;
import static sorisoop.soridam.infra.config.data.replication.DataSourceType.MASTER;
import static sorisoop.soridam.infra.config.data.replication.DataSourceType.SLAVE;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@EnableJpaRepositories(  // # 1
	basePackages = {"sorisoop.soridam.infra.repository.jpa"},
	entityManagerFactoryRef = "entityManagerFactory",
	transactionManagerRef = "transactionManager"
)
@Configuration
public class RoutingDataSourceConfiguration {

	private final String ROUTING_DATA_SOURCE = "ROUTING_DATA_SOURCE";
	private final String DATA_SOURCE = "DATA_SOURCE";

	@Bean(ROUTING_DATA_SOURCE)
	public DataSource routingDataSource(
		@Qualifier(MASTER_DATA_SOURCE) final DataSource masterDataSource,  // # 2
		@Qualifier(SLAVE_DATA_SOURCE) final DataSource slaveDataSource) {

		// # 3
		RoutingDataSource routingDataSource = new RoutingDataSource();

		// # 4
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(MASTER, masterDataSource);
		dataSourceMap.put(SLAVE, slaveDataSource);

		// # 5
		routingDataSource.setTargetDataSources(dataSourceMap);
		// # 6
		routingDataSource.setDefaultTargetDataSource(masterDataSource);

		return routingDataSource;
	}

	@Bean(DATA_SOURCE)
	public DataSource dataSource( // # 7
		@Qualifier(ROUTING_DATA_SOURCE) DataSource routingDataSource) {
		return new LazyConnectionDataSourceProxy(routingDataSource);
	}

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory( // # 8
		@Qualifier(DATA_SOURCE) DataSource dataSource) {
		// # 9
		LocalContainerEntityManagerFactoryBean entityManagerFactory
			= new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan("sorisoop.soridam");
		entityManagerFactory.setJpaVendorAdapter(this.jpaVendorAdapter());
		entityManagerFactory.setPersistenceUnitName("entityManager");

		Map<String, Object> jpaProperties = new HashMap<>();
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		entityManagerFactory.setJpaPropertyMap(jpaProperties);

		return entityManagerFactory;
	}

	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		return hibernateJpaVendorAdapter;
	}

	@Bean("transactionManager")
	public PlatformTransactionManager platformTransactionManager( // # 10
		@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return jpaTransactionManager;
	}

}
