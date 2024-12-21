package sorisoop.soridam.infra.config.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.infra.config.jpa.JpaAuditingConfig;
import sorisoop.soridam.infra.config.jpa.JpaConfig;
import sorisoop.soridam.infra.config.properties.PropertiesConfig;
import sorisoop.soridam.infra.config.swagger.SwaggerConfig;

@Getter
@RequiredArgsConstructor
public enum SoriDamConfigGroup {
	JPA(JpaConfig.class),
	JPA_AUDITING(JpaAuditingConfig.class),
	PROPERTIES(PropertiesConfig.class),
	SWAGGER(SwaggerConfig.class);

	private final Class<? extends SoriDamConfig> configClass;
}
