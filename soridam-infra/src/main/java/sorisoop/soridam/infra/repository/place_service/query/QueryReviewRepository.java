package sorisoop.soridam.infra.repository.place_service.query;

import static sorisoop.soridam.domain.place_service.place.domain.QPlace.place;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place_service.place.domain.Place;
import sorisoop.soridam.domain.place_service.place.domain.enums.Category;

@Repository
@RequiredArgsConstructor
public class QueryReviewRepository {
	private final JPAQueryFactory queryFactory;

	public List<Place> findNearAddressesByPoint(Point point, int distanceMeter, List<Category> categories) {
		BooleanBuilder builder = new BooleanBuilder();

		if (categories != null && !categories.isEmpty()) {
			builder.and(place.category.in(categories));
		}

		builder.and(isWithinDistance(place.location, point, distanceMeter));

		return queryFactory.selectFrom(place)
			.where(builder)
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
