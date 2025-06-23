package sorisoop.soridam.domain.place.place.application;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.document.PlaceDocument;
import sorisoop.soridam.domain.place.place.domain.PlaceRepository;

@Service
@RequiredArgsConstructor
public class PlaceDocumentService {
	private final PlaceRepository placeRepository;

	@Async
	public void saveAllAsync(List<PlaceDocument> placeDocuments) {
		placeRepository.saveAllDocuments(placeDocuments);
	}
}
