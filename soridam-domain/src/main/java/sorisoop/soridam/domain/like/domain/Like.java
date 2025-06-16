package sorisoop.soridam.domain.like.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.common.BaseTimeEntity;
import sorisoop.soridam.domain.user.user.domain.User;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Table(name = "likes", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"user_id", "like_type", "target_id"})
})
public class Like extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Enumerated(STRING)
	@Column(nullable = false, name = "like_type")
	private LikeType likeType;

	@Column(nullable = false, name = "target_id")
	private Long targetId;

	public static Like create(User user, LikeType likeType, Long targetId) {
		return Like.builder()
			.user(user)
			.likeType(likeType)
			.targetId(targetId)
			.build();
	}
}
