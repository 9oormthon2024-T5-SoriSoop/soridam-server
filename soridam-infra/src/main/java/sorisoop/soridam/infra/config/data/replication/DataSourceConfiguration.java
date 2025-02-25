package sorisoop.soridam.infra.config.data.replication;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfiguration {
	public static final String MASTER_DATA_SOURCE = "MASTER_DATA_SOURCE";
	public static final String SLAVE_DATA_SOURCE = "SLAVE_DATA_SOURCE";

	@Bean(MASTER_DATA_SOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource masterDataSource() {
		HikariDataSource dataSource = DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.build();
		dataSource.setPoolName("master");
		return dataSource;
	}

	@Bean(SLAVE_DATA_SOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSource slaveDataSource() {
		HikariDataSource dataSource = DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.build();
		dataSource.setPoolName("slave");
		return dataSource;
	}
}
