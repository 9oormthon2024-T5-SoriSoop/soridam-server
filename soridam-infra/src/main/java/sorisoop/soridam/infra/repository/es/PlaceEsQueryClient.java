package sorisoop.soridam.infra.repository.es;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.GeoLocation;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionBoostMode;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiValueMode;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceConfig;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.activitylog.domain.ActivityLog;
import sorisoop.soridam.domain.place.place.domain.PlaceEsQueryPort;


@Component
@RequiredArgsConstructor
public class PlaceEsQueryClient implements PlaceEsQueryPort {
	private final ElasticsearchClient elasticsearchClient;

	@Override
	public List<Long> getMyRecentPlaceIds(Long userId) throws IOException {
		SearchResponse<ActivityLog> response = elasticsearchClient.search(s -> s
				.index("activity_log-*")
				.query(Query.of(q -> q
					.term(t -> t.field("userId").value(userId))
				))
				.sort(SortOptions.of(so -> so
					.field(f -> f.field("createdAt").order(SortOrder.Desc))
				))
				.size(200)
				.source(SourceConfig.of(sc -> sc
					.filter(sf -> sf.includes("placeId"))
				)),
			ActivityLog.class
		);

		return response.hits().hits().stream()
			.map(hit -> {
				assert hit.source() != null;
				return hit.source().getPlaceId();
			})
			.toList();
	}

	@Override
	public List<Long> getSimilarUserIds(List<Long> placeIds, Long userId) throws IOException {
		SearchResponse<ActivityLog> response = elasticsearchClient.search(s -> s
				.index("activity_log-*")
				.query(q -> q
					.bool(b -> b
						.must(m -> m
							.terms(t -> t
								.field("placeId")
								.terms(tq -> tq.value(
									placeIds.stream()
										.map(FieldValue::of)
										.toList()
								))
							)
						)
						.mustNot(mn -> mn
							.term(t -> t
								.field("userId")
								.value(userId)
							)
						)
					)
				)
				.size(500)
				.source(SourceConfig.of(sc -> sc
					.filter(sf -> sf.includes("userId"))
				)),
			ActivityLog.class
		);

		return response.hits().hits().stream()
			.map(hit -> hit.source().getUserId())
			.distinct()
			.toList();
	}

	@Override
	public Map<Long, Double> getRecommendedPlaces(List<Long> similarUserIds, List<Long> excludePlaceIds,
		double userCurrentLat, double userCurrentLon) throws IOException {
		SearchResponse<ActivityLog> response = elasticsearchClient.search(s -> s
				.index("activity_log-*")
				.query(q -> q
					.functionScore(fs -> fs
						.query(innerQuery -> innerQuery
							.bool(b -> b
									.must(m -> m
										.terms(t -> t
											.field("userId")
											.terms(tq -> tq.value(
												similarUserIds.stream()
													.map(FieldValue::of)
													.toList()
											))
										)
									)
									.mustNot(mn -> mn
										.terms(t -> t
											.field("placeId")
											.terms(tq -> tq.value(
												excludePlaceIds.stream()
													.map(FieldValue::of)
													.toList()
											))
										)
									)
							)
						)
						.functions(f -> f
							.gauss(g -> g
								.geo(geo -> geo
									.field("latlon")
									.placement(p -> {
										GeoLocation userLocation = GeoLocation.of(o -> o.latlon(ll -> ll
											.lat(userCurrentLat)
											.lon(userCurrentLon)
										));
										return p
											.origin(userLocation)
											.scale("1km")
											.offset("0km")
											.decay(0.5);
									})
									.multiValueMode(MultiValueMode.Avg)
								)
							)
						)
						.boostMode(FunctionBoostMode.Multiply)
					)
				)
				.size(10000)
				.source(SourceConfig.of(sc -> sc
					.filter(sf -> sf.includes("placeId", "customScore", "activityType", "reviewTags", "latlon"))
				)),
			ActivityLog.class
		);

		return response.hits().hits().stream()
			.map(Hit::source)
			.filter(java.util.Objects::nonNull)
			.collect(Collectors.groupingBy(
				ActivityLog::getPlaceId,
				Collectors.summingDouble(ActivityLog::getScore)
			));
	}
}