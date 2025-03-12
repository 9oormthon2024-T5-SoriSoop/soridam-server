package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.domain.QNoise;
import sorisoop.soridam.domain.noise.domain.Radius;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryNoiseRepository {
	private final JPAQueryFactory jpaQueryFactory;

	public List<Noise> getNearbyNoises(Point point) {
		QNoise noise = QNoise.noise;

		return jpaQueryFactory.selectFrom(noise)
			.where(
				isWithinDistance(noise.address.location, point, 0.0000918 * 50)
			)
			.fetch();
	}

	public List<Noise> findByAvgDecibelAndPoint(Point point, Radius radius, NoiseLevel noiseLevel) {
		QNoise noise = QNoise.noise;

		return jpaQueryFactory.selectFrom(noise)
			.where(
				noise.avgDecibel.between(noiseLevel.getMinDecibel(), noiseLevel.getMaxDecibel()),
				isWithinDistance(noise.address.location, point, radius.getRadiusInMeters())
			)
			.fetch();
	}

	private BooleanExpression isWithinDistance(ComparablePath<Point> noisePoint, Point targetPoint, double distance) {
		return Expressions.booleanTemplate(
			"ST_DWithin(ST_Transform({0}, 5186), " +
				"ST_Transform(ST_SetSRID({1}, 4326), 5186), {2})",
			noisePoint,
			targetPoint,
			distance
		).eq(true);
	}
}