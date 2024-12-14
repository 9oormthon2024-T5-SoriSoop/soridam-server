package com.soridam.server.config;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeometryConfig {
	@Bean
	public GeometryFactory GeometryConfig() {
		return new GeometryFactory(new PrecisionModel(), 5181);
	}
}
