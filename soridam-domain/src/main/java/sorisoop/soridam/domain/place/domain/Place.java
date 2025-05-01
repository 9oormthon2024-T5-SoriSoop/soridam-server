package sorisoop.soridam.domain.place.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.place.domain.enums.Category;

@Entity
@Table(
	name = "address",
	uniqueConstraints = @UniqueConstraint(columnNames = {"roadAddress", "placeName"}),
	indexes = {
		@Index(name = "idx_address_road_place", columnList = "roadAddress, placeName")
	}
)
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Place {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "geometry(Point, 4326)")
	private Point location;

	@Column(nullable = false)
	private String roadAddress;

	@Column(nullable = false)
	private String regionAddress;

	@Enumerated(STRING)
	private Category category;

	private String placeName;

	private String placeUrl;

	public static Place create(Point location, String roadAddress, String regionAddress, Category category, String placeName, String placeUrl) {
		return Place.builder()
			.location(location)
			.roadAddress(roadAddress)
			.regionAddress(regionAddress)
			.category(category)
			.placeName(placeName)
			.placeUrl(placeUrl)
			.build();
	}
}
