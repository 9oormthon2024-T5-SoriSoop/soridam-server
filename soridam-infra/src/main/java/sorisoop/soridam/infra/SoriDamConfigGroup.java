package sorisoop.soridam.infra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.infra.config.JpaAuditingConfig;
import sorisoop.soridam.infra.config.JpaConfig;
import sorisoop.soridam.infra.config.PropertiesConfig;
import sorisoop.soridam.infra.config.SwaggerConfig;

@Getter
@RequiredArgsConstructor
public enum SoriDamConfigGroup {
	JPA(JpaConfig.class),
	JPA_AUDITING(JpaAuditingConfig.class),
	PROPERTIES(PropertiesConfig.class),
	SWAGGER(SwaggerConfig.class);

	private final Class<? extends SoriDamConfig> configClass;
}
