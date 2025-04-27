package sorisoop.soridam.domain.address.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.address.domain.enums.Category;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Address {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "geometry(Point, 4326)")
	private Point location;

	@Column(nullable = false, unique = true)
	private String roadAddress;

	@Column(nullable = false)
	private String regionAddress;

	@Enumerated(STRING)
	@Column(nullable = false)
	private Category category;

	public static Address create(Point location, String roadAddress, String regionAddress, Category category) {
		return Address.builder()
			.location(location)
			.roadAddress(roadAddress)
			.regionAddress(regionAddress)
			.category(category)
			.build();
	}
}
