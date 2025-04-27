package sorisoop.soridam.domain.review.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.globalutil.uuid.UuidPrefix.REVIEW;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.common.BaseTimeEntity;
import sorisoop.soridam.domain.common.UuidExtractable;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.globalutil.uuid.PrefixedUuid;
import sorisoop.soridam.globalutil.uuid.UuidPrefix;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Review extends BaseTimeEntity implements UuidExtractable {
	@Id
	@PrefixedUuid(REVIEW)
	private String id;

	@Column(nullable = false)
	private Long targetId;

	@Enumerated(STRING)
	@Column(nullable = false, length = 25)
	private UuidPrefix reviewType;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(nullable = false, precision = 2, scale = 1)
	private BigDecimal rating;

	public static Review create(Long targetId, UuidPrefix reviewType, User author, String content, BigDecimal rating) {
		return Review.builder()
			.targetId(targetId)
			.reviewType(reviewType)
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
}