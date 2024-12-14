package com.soridam.server.noise.repository;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.domain.QNoise;
import com.soridam.server.noise.dto.enums.NoiseLevel;
import com.soridam.server.noise.dto.enums.Radius;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryNoiseRepository {
	private final JPAQueryFactory jpaQueryFactory;

	public List<Noise> getNearbyNoises(Point point) {
		QNoise noise = QNoise.noise;

		return jpaQueryFactory.selectFrom(noise)
			.where(
				Expressions.booleanTemplate(
					"ST_DWithin({0}, ST_SetSRID({1}, 5181), {2})",
					noise.point,
					point,
					0.000459 //0.0000918(1m) * 50
				).eq(true)
			)
			.fetch();
	}
  
	public List<Noise> findByAvgDecibleAndPoint(Point point, Radius radius, NoiseLevel noiseLevel) {
		QNoise noise = QNoise.noise;

		return jpaQueryFactory.selectFrom(noise)
			.where(
				noise.avgDecibel.between(noiseLevel.getMinDecibel(), noiseLevel.getMaxDecibel()),
				Expressions.booleanTemplate(
					"ST_DWithin({0}, ST_SetSRID({1}, 5181), {2})",
					noise.point,
					point,
					radius.getRadiusInMeters() * 0.0000918
				).eq(true)
			)
			.fetch();
	}
}
