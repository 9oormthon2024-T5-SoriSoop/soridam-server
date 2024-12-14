package com.soridam.server.noise.repository;

import java.time.LocalDateTime;
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

	public List<Noise> findByAvgDecibleAndPoint(Point point, Radius radius, NoiseLevel noiseLevel) {
		QNoise noise = QNoise.noise;

		return jpaQueryFactory.selectFrom(noise)
			.where(
				noise.avgDecibel.between(noiseLevel.getMinDecibel(), noiseLevel.getMaxDecibel()),
				Expressions.booleanTemplate(
					"ST_DWithin({0}, ST_SetSRID(ST_MakePoint({1}, {2}), 5181), {3})",
					noise.point,
					point.getX(),
					point.getY(),
					radius.getRadiusInMeters()
				),
				noise.createdAt.after(LocalDateTime.now().minusHours(1))
			)
			.fetch();
	}
}
