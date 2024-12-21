package sorisoop.soridam.infra.config.jpa;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import sorisoop.soridam.infra.config.base.SoriDamConfig;

@EnableJpaAuditing
public class JpaAuditingConfig implements SoriDamConfig {
}
