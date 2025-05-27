package sorisoop.soridam.domain.reward.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.domain.reward.domain.RedemptionStatus.APPROVED;
import static sorisoop.soridam.domain.reward.domain.RedemptionStatus.PENDING;
import static sorisoop.soridam.domain.reward.domain.RedemptionStatus.REJECTED;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
import sorisoop.soridam.domain.rewarditem.domain.RewardItem;
import sorisoop.soridam.domain.user.user.domain.User;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
public class PointRedemption extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "good_id", nullable = false)
	private RewardItem rewardItem;

	@Enumerated(STRING)
	@Column(nullable = false)
	private RedemptionStatus status;

	@Column(length = 100)
	private String issuedCode;

	private String adminComment;

	private LocalDateTime redeemedAt;

	public static PointRedemption create(User user, RewardItem rewardItem) {
		return PointRedemption.builder()
			.user(user)
			.rewardItem(rewardItem)
			.status(PENDING)
			.build();
	}
	public void approve(String issuedCode) {
		this.status = APPROVED;
		this.issuedCode = issuedCode;
		this.redeemedAt = LocalDateTime.now();
	}

	public void reject(String adminComment) {
		this.adminComment = adminComment;
		this.status = REJECTED;
	}
}

