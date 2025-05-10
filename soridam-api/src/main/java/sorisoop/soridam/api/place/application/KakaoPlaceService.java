package sorisoop.soridam.api.place.application;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.application.PlaceCommandService;
import sorisoop.soridam.domain.place.domain.enums.Category;
import sorisoop.soridam.domain.place.domain.enums.Region;
import sorisoop.soridam.infra.kakao.KakaoMapClient;
import sorisoop.soridam.infra.kakao.KakaoPlaceResponse;

@Service
@RequiredArgsConstructor
public class KakaoPlaceService {
	private final KakaoMapClient kakaoMapClient;
	private final PlaceCommandService placeCommandService;

	private static final double X_GAP = 0.011;    // 경도 1km 간격
	private static final double Y_GAP = 0.009;    // 위도 1km 간격
	private static final int GRID = 10;           // 10x10 격자
	private static final int RADIUS = 1000;       // 1km 반경

	public void importPlacesForAllRegions() {
		for (Region region : Region.values()) {
			for (Category category : Category.values()) {
				importPlacesByGrid(region, category);
			}
		}
	}

	private void importPlacesByGrid(Region region, Category category) {
		Set<String> seenPlaceIds = new HashSet<>();

		for (int i = 0; i < GRID; i++) {
			for (int j = 0; j < GRID; j++) {
				double x = region.getX() + (X_GAP * i);
				double y = region.getY() + (Y_GAP * j);
				int page = 1;
				boolean isEnd = false;
				int sameCount = 0;

				while (!isEnd && sameCount < 3) {
					KakaoPlaceResponse response = kakaoMapClient.searchPlaces(category.name(), x, y, RADIUS, page);
					if (response.getDocuments() == null || response.getDocuments().isEmpty()) break;

					isEnd = response.getMeta().is_end();
					int beforeSize = seenPlaceIds.size();

					for (KakaoPlaceResponse.KakaoPlace place : response.getDocuments()) {
						String key = place.getRoad_address_name() + "::" + place.getPlace_name();
						if (!seenPlaceIds.add(key)) continue;

						placeCommandService.saveIfNotExists(
							Double.parseDouble(place.getX()),
							Double.parseDouble(place.getY()),
							place.getRoad_address_name(),
							place.getAddress_name(),
							Category.fromCode(place.getCategory_group_code()),
							place.getPlace_name(),
							place.getPlace_url()
						);
					}

					if (seenPlaceIds.size() == beforeSize) {
						sameCount++;
					} else {
						sameCount = 0;
					}
					page++;
				}
			}
		}
	}

}

