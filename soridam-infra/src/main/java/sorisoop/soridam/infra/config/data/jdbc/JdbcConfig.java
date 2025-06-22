package sorisoop.soridam.infra.config.data.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfig {
	private final DataSource masterDataSource;

	public JdbcConfig(@Qualifier("MASTER_DATA_SOURCE") DataSource masterDataSource) {
		this.masterDataSource = masterDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(masterDataSource);
	}
}
