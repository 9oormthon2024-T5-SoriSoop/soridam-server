package sorisoop.soridam.infra.repository.query;

import static sorisoop.soridam.domain.favorite.domain.QFavoritePlace.favoritePlace;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.favorite.domain.FavoritePlace;

@Repository
@RequiredArgsConstructor
public class QueryFavoritePlaceRepository {
	private final JPAQueryFactory jpaQueryFactory;

	public List<FavoritePlace> findByUserIdWithCursor(Long userId, Long lastId, int limit) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(favoritePlace.user.id.eq(userId));
		if (lastId != null) {
			builder.and(favoritePlace.id.lt(lastId));
		}

		return jpaQueryFactory.selectFrom(favoritePlace)
			.where(builder)
			.orderBy(favoritePlace.id.desc())
			.limit(limit)
			.fetch();
	}
}
