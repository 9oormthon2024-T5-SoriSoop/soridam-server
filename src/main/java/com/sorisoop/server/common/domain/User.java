package com.sorisoop.server.common.domain;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

import java.util.List;

import com.sorisoop.server.noise.domain.Noise;
import com.sorisoop.server.review.domain.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "\"user\"")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class User {
	@Id
	private Long id;

	private String name;

	private String password;

	private int point;

	@OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
	private List<Noise> decibels;

	@OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
	private List<Review> reviews;
}
