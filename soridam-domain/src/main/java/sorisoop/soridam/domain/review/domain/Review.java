package sorisoop.soridam.domain.review.domain;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.globalutil.uuid.UuidPrefix.REVIEW;
import static sorisoop.soridam.globalutil.uuid.UuidPrefix.USER;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.common.domain.BaseTimeEntity;
import sorisoop.soridam.common.domain.UuidExtractable;
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
	private Long id;

	@Column(nullable = false)
	private String targetId;

	@Enumerated(STRING)
	@Column(nullable = false, length = 25)
	private UuidPrefix reviewType;

	@Column(nullable = false)
	private String authorId;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(nullable = false, precision = 2, scale = 1)
	private BigDecimal rating;

	public static Review create(String targetId, UuidPrefix reviewType, String authorId, String content, BigDecimal rating) {
		return Review.builder()
			.targetId(reviewType.getPrefix() + targetId)
			.reviewType(reviewType)
			.authorId(USER.getPrefix() + authorId)
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