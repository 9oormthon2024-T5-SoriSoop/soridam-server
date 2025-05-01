package sorisoop.soridam.infra.repository.jpa;

import static com.querydsl.core.types.Order.ASC;
import static com.querydsl.core.types.Order.DESC;
import static sorisoop.soridam.domain.noise.domain.QNoise.noise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;

@Repository
@RequiredArgsConstructor
public class QueryNoiseRepository {
	private final JPAQueryFactory queryFactory;
	private static final PathBuilder<Noise> noisePath = new PathBuilder<>(Noise.class, "noise");

	public List<Noise> findByPlaceWithCursorAndAvgDecibelRange(
		Long placeId,
		String lastValue,
		int minAvg,
		int maxAvg,
		int limit,
		Sort sort
	) {
		BooleanBuilder builder = new BooleanBuilder();

		builder.and(noise.place.id.eq(placeId));
		builder.and(noise.avgDecibel.between(minAvg, maxAvg));

		if (lastValue != null) {
			applyCursorCondition(builder, lastValue, sort);
		}

		return queryFactory.selectFrom(noise)
			.where(builder)
			.orderBy(convertSort(sort))
			.limit(limit)
			.fetch();
	}

	private void applyCursorCondition(BooleanBuilder builder, String lastValue, Sort sort) {
		for (Sort.Order order : sort) {
			String property = order.getProperty();
			boolean isAsc = order.isAscending();

			switch (property) {
				case "id" -> {
					Long idCursor = Long.parseLong(lastValue);
					builder.and(isAsc ? noise.id.gt(idCursor) : noise.id.lt(idCursor));
				}
				case "avgDecibel" -> {
					Integer decibelCursor = Integer.parseInt(lastValue);
					builder.and(isAsc ? noise.avgDecibel.gt(decibelCursor) : noise.avgDecibel.lt(decibelCursor));
				}
				default -> throw new IllegalArgumentException("지원하지 않는 정렬 필드입니다: " + property);
			}
		}
	}

	private OrderSpecifier<?>[] convertSort(Sort sort) {
		List<OrderSpecifier<?>> orders = new ArrayList<>();

		for (Sort.Order order : sort) {
			OrderSpecifier<?> specifier = new OrderSpecifier<>(
				order.isAscending() ? ASC : DESC,
				noisePath.getComparable(order.getProperty(), Comparable.class)
			);
			orders.add(specifier);
		}

		boolean sortedByAvg = sort.stream()
			.anyMatch(order -> order.getProperty().equals("avgDecibel"));

		if (sortedByAvg) {
			orders.add(new OrderSpecifier<>(DESC, noise.id));
		}

		return orders.toArray(new OrderSpecifier[0]);
	}

}
