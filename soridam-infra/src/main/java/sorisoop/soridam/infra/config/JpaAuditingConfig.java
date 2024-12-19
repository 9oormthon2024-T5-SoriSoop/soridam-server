package sorisoop.soridam.infra.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import sorisoop.soridam.infra.SoriDamConfig;

@EnableJpaAuditing
public class JpaAuditingConfig implements SoriDamConfig {
}
