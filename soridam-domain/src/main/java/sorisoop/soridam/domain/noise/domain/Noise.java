package sorisoop.soridam.domain.noise.domain;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.globalutil.uuid.UuidPrefix.NOISE;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.common.domain.BaseTimeEntity;
import sorisoop.soridam.common.domain.UuidExtractable;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.globalutil.uuid.PrefixedUuid;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Noise extends BaseTimeEntity implements UuidExtractable {
	@Id
	@PrefixedUuid(NOISE)
	private String id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false, columnDefinition = "geometry(Point,5181)")
	private Point point;

	@Column(nullable = false)
	private int maxDecibel;

	@Column(nullable = false)
	private int avgDecibel;

	@Column(nullable = false)
	private String review;

	public static Noise create(User user, Point point, int maxDecibel,
		int avgDecibel, String review) {
		return builder()
			.user(user)
			.point(point)
			.maxDecibel(maxDecibel)
			.avgDecibel(avgDecibel)
			.review(review)
			.build();
	}
}
