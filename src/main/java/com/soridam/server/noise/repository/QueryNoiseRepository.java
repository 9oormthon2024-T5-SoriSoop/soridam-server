package com.soridam.server.noise.repository;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.domain.QNoise;

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
					"ST_DWithin({0}, ST_SetSRID(ST_MakePoint({1}), 5181), {2})",
					noise.point,
					point,
					50
				).eq(true)
			)
			.fetch();
	}
}
