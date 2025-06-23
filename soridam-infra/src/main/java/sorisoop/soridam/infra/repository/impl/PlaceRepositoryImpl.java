package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.document.PlaceDocument;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.place.place.domain.PlaceRepository;
import sorisoop.soridam.domain.place.place.dto.PlaceInsertDto;
import sorisoop.soridam.domain.place.place.domain.enums.Category;
import sorisoop.soridam.infra.repository.es.DocumentPlaceRepository;
import sorisoop.soridam.infra.repository.jdbc.JdbcPlaceRepository;
import sorisoop.soridam.infra.repository.jpa.JpaPlaceRepository;
import sorisoop.soridam.infra.repository.query.QueryReviewRepository;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepository {
	private final JpaPlaceRepository jpaPlaceRepository;
	private final QueryReviewRepository queryReviewRepository;
	private final JdbcPlaceRepository jdbcPlaceRepository;
	private final DocumentPlaceRepository documentPlaceRepository;

	@Override
	public Place save(Place place) {
		return jpaPlaceRepository.save(place);
	}

	@Override
	public Optional<Place> findById(Long id) {
		return jpaPlaceRepository.findById(id);
	}

	@Override
	public List<Place> findAll() {
		return jpaPlaceRepository.findAll();
	}

	@Override
	public void delete(Place place) {
		jpaPlaceRepository.delete(place);
	}

	@Override
	public Optional<Place> findByRoadAddressAndPlaceName(String roadAddress, String placeName) {
		return jpaPlaceRepository.findByRoadAddressAndPlaceName(roadAddress, placeName);
	}

	@Override
	public List<Place> findNearPlacesByPoint(Point point, int distanceMeter, List<Category> categories) {
		return queryReviewRepository.findNearAddressesByPoint(point, distanceMeter, categories);
	}

	@Override
	public boolean existsByRoadAddressAndPlaceName(String roadAddress, String placeName) {
		return jpaPlaceRepository.existsByRoadAddressAndPlaceName(roadAddress, placeName);
	}

	@Override
	public void saveAll(List<PlaceInsertDto> places) {
		jdbcPlaceRepository.batchInsert(places);
	}

	@Override
	public void saveAllDocuments(List<PlaceDocument> placeDocuments) {
		documentPlaceRepository.saveAll(placeDocuments);
	}
}
