package sorisoop.soridam.api.place.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.application.PlaceCommandService;
import sorisoop.soridam.domain.place.place.dto.PlaceInsertDto;
import sorisoop.soridam.domain.place.place.domain.enums.Category;
import sorisoop.soridam.domain.place.place.domain.enums.Region;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;
import sorisoop.soridam.infra.kakao.KakaoMapClient;
import sorisoop.soridam.infra.kakao.KakaoPlaceResponse;

@Service
@RequiredArgsConstructor
public class KakaoPlaceService {
	private final KakaoMapClient kakaoMapClient;
	private final PlaceCommandService placeCommandService;
	private final GeometryUtils geometryUtils;

	private static final double X_GAP = 0.011;    // 경도 1km 간격
	private static final double Y_GAP = 0.009;    // 위도 1km 간격
	private static final int GRID = 10;           // 10x10 격자
	private static final int RADIUS = 1000;       // 1km 반경

	public void importPlacesForAllRegions() {
		for (Region region : Region.values()) {
			Set<String> seenPlaceIds = new HashSet<>();
			List<PlaceInsertDto> batch = new ArrayList<>();

			for (Category category : Category.values()) {
				List<PlaceInsertDto> partial = importPlacesByGrid(region, category, seenPlaceIds);
				batch.addAll(partial);
			}

			placeCommandService.saveAll(batch);
		}
	}

	private List<PlaceInsertDto> importPlacesByGrid(Region region, Category category, Set<String> seenPlaceIds) {
		List<PlaceInsertDto> batch = new ArrayList<>();
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
						Point location = geometryUtils.createPoint(
							Double.parseDouble(place.getX()),
							Double.parseDouble(place.getY())
						);

						PlaceInsertDto dto = PlaceInsertDto.of(
							location,
							place.getRoad_address_name(),
							place.getAddress_name(),
							Category.fromCode(place.getCategory_group_code()),
							place.getPlace_name(),
							place.getPlace_url()
						);
						String key = dto.generateKey();
						if (!seenPlaceIds.add(key)) continue;

						batch.add(dto);
					}

					if (seenPlaceIds.size() == beforeSize) sameCount++;
					else sameCount = 0;
					page++;
				}
			}
		}

		return batch;
	}

}

