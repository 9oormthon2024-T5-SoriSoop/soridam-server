package sorisoop.soridam.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import sorisoop.soridam.infra.EnableSoriDamConfig;
import sorisoop.soridam.infra.SoriDamConfigGroup;

@Configuration(proxyBeanMethods = false)
@ComponentScan("sorisoop.soridam")
@EnableSoriDamConfig({
	SoriDamConfigGroup.JPA,
	SoriDamConfigGroup.JPA_AUDITING,
	SoriDamConfigGroup.PROPERTIES,
	SoriDamConfigGroup.SWAGGER
})
class InfraConfig {

}
