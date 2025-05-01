package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.domain.Place;
import sorisoop.soridam.domain.place.domain.PlaceRepository;
import sorisoop.soridam.domain.place.domain.enums.Category;
import sorisoop.soridam.infra.repository.jpa.JpaAddressRepository;
import sorisoop.soridam.infra.repository.jpa.QueryAddressRepository;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepository {
	private final JpaAddressRepository jpaAddressRepository;
	private final QueryAddressRepository queryAddressRepository;

	@Override
	public Place save(Place place) {
		return jpaAddressRepository.save(place);
	}

	@Override
	public Optional<Place> findById(Long id) {
		return jpaAddressRepository.findById(id);
	}

	@Override
	public List<Place> findAll() {
		return jpaAddressRepository.findAll();
	}

	@Override
	public void delete(Place place) {
		jpaAddressRepository.delete(place);
	}

	@Override
	public Optional<Place> findByRoadAddressAndPlaceName(String roadAddress, String placeName) {
		return jpaAddressRepository.findByRoadAddressAndPlaceName(roadAddress, placeName);
	}

	@Override
	public List<Place> findNearAddressesByPoint(Point point, int distanceMeter, List<Category> categories) {
		return queryAddressRepository.findNearAddressesByPoint(point, distanceMeter, categories);
	}
}
