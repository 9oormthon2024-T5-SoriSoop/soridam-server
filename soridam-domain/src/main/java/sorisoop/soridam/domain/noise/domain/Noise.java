package sorisoop.soridam.domain.noise.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.place.domain.Place;
import sorisoop.soridam.domain.common.BaseTimeEntity;
import sorisoop.soridam.domain.user.domain.User;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Noise extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	private Place place;

	@Column(nullable = false)
	private int maxDecibel;

	@Column(nullable = false)
	private int avgDecibel;

	public static Noise create(User user, Place place, int maxDecibel,
		int avgDecibel) {
		return builder()
			.user(user)
			.place(place)
			.maxDecibel(maxDecibel)
			.avgDecibel(avgDecibel)
			.build();
	}
}
