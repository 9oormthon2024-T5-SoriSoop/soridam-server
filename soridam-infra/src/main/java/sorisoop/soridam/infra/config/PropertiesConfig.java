package sorisoop.soridam.infra.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import sorisoop.soridam.infra.SoriDamConfig;

@ConfigurationPropertiesScan(basePackages = "sorisoop.soridam")
public class PropertiesConfig implements SoriDamConfig {
}

