package sorisoop.soridam.domain.place.place.dto;

import org.locationtech.jts.geom.Point;

import lombok.Builder;
import sorisoop.soridam.domain.place.place.domain.enums.Category;

@Builder
public record PlaceInsertDto(
	Point location,
	String roadAddress,
	String regionAddress,
	Category category,
	String placeName,
	String placeUrl
) {
	public String generateKey() {
		return roadAddress + "::" + placeName;
	}

	public static PlaceInsertDto of(Point location, String roadAddress, String regionAddress, Category category, String placeName, String placeUrl) {
		return PlaceInsertDto.builder()
			.location(location)
			.roadAddress(roadAddress)
			.regionAddress(regionAddress)
			.category(category)
			.placeName(placeName)
			.placeUrl(placeUrl)
			.build();
	}
}
