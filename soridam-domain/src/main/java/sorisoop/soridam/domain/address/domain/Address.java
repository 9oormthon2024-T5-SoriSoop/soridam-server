package sorisoop.soridam.domain.address.domain;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.globalutil.uuid.UuidPrefix.ADDRESS;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.address.domain.enums.Category;
import sorisoop.soridam.globalutil.uuid.PrefixedUuid;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Address {
	@Id
	@PrefixedUuid(ADDRESS)
	private String id;

	@Column(nullable = false, columnDefinition = "geometry(Point, 4326)")
	private Point location;

	@Column(nullable = false)
	private String roadAddress;

	@Column(nullable = false)
	private String regionAddress;

	@Enumerated(STRING)
	private Category category;

	public static Address create(Point location, String roadAddress, String regionAddress) {
		return Address.builder()
			.location(location)
			.roadAddress(roadAddress)
			.regionAddress(regionAddress)
			.build();
	}
}
