package sorisoop.soridam.domain.review.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.common.BaseTimeEntity;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.user.user.domain.User;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Review extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "place_id", nullable = false)
	private Place place;

	@ElementCollection(targetClass = ReviewTag.class)
	@CollectionTable(name = "review_tags", joinColumns = @JoinColumn(name = "review_id"))
	@Enumerated(STRING)
	@Column(name = "tag")
	private Set<ReviewTag> tags;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(nullable = false, precision = 2, scale = 1)
	private BigDecimal rating;

	public static Review create(Place place, Set<ReviewTag> tags, User author, String content, BigDecimal rating) {
		return Review.builder()
			.place(place)
			.tags(tags != null ? tags : new HashSet<>())
			.author(author)
			.content(content)
			.rating(rating)
			.build();
	}

	public void updateContent(String content) {
		this.content = content;
	}

	public void updateRating(BigDecimal rating) {
		this.rating = rating;
	}

	public void updateTags(Set<ReviewTag> tags) {
		this.tags.clear();
		if (tags != null) {
			this.tags.addAll(tags);
		}
	}
}