package com.soridam.server.noise.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import org.locationtech.jts.geom.Point;

import com.soridam.server.common.domain.BaseTimeEntity;
import com.soridam.server.common.domain.User;

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

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Noise extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

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
		return Noise.builder()
			.user(user)
			.point(point)
			.maxDecibel(maxDecibel)
			.avgDecibel(avgDecibel)
			.review(review)
			.build();
	}
}
