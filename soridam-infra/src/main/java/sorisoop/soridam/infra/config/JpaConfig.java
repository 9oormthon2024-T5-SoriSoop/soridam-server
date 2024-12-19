package sorisoop.soridam.infra.config;

import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import sorisoop.soridam.infra.SoriDamConfig;

@EntityScan(basePackages = "sorisoop.soridam.domain")
@Configuration
@EnableJpaRepositories(basePackages = "sorisoop.soridam")
public class JpaConfig implements SoriDamConfig {
	private final EntityManager entityManager;

	public JpaConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}

	@Bean
	public GeometryFactory geometryFactory() {
		return new GeometryFactory();
	}
}
