package sorisoop.soridam.domain.noise.infrastructure;

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
				isWithinDistance(noise.point, point, 0.0000918 * 50)
			)
			.fetch();
	}
  
	public List<Noise> findByAvgDecibleAndPoint(Point point, Radius radius, NoiseLevel noiseLevel) {
		QNoise noise = QNoise.noise;

		return jpaQueryFactory.selectFrom(noise)
			.where(
				noise.avgDecibel.between(noiseLevel.getMinDecibel(), noiseLevel.getMaxDecibel()),
				isWithinDistance(noise.point, point, radius.getRadiusInMeters() * 0.0000918)
			)
			.fetch();
	}

	private BooleanExpression isWithinDistance(ComparablePath<Point> noisePoint, Point targetPoint, double distance) {
		return Expressions.booleanTemplate(
			"ST_DWithin({0}, ST_SetSRID({1}, 5181), {2})",
			noisePoint,
			targetPoint,
			distance
		).eq(true);
	}
}
