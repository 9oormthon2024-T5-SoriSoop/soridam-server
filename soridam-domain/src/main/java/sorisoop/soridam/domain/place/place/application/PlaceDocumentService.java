package sorisoop.soridam.domain.place.place.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.domain.PlaceRepository;

@Service
@RequiredArgsConstructor
public class PlaceDocumentService {
	private final PlaceRepository placeRepository;


}
