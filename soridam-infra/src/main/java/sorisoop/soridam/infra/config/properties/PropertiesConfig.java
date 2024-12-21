package sorisoop.soridam.infra.config.properties;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import sorisoop.soridam.infra.config.base.SoriDamConfig;

@ConfigurationPropertiesScan(basePackages = "sorisoop.soridam.api")
public class PropertiesConfig implements SoriDamConfig {
}

