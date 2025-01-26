package sorisoop.soridam.infra.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import sorisoop.soridam.infra.config.base.SoriDamConfig;

@EntityScan(basePackages = "sorisoop.soridam.domain")
@Configuration
@EnableJpaRepositories(basePackages = "sorisoop.soridam")
public class JpaConfig implements SoriDamConfig {
}
