package sorisoop.soridam.infra.config;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sorisoop.soridam.infra.SoriDamConfig;

@Configuration
public class GeometryConfig implements SoriDamConfig {
	@Bean
	public GeometryFactory GeometryConfig() {
		return new GeometryFactory(new PrecisionModel(), 5181);
	}
}
