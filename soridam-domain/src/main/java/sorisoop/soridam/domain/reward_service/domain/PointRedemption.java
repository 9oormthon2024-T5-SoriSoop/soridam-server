package sorisoop.soridam.domain.reward_service.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.domain.reward_service.domain.RedemptionStatus.APPROVED;
import static sorisoop.soridam.domain.reward_service.domain.RedemptionStatus.REJECTED;

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
import sorisoop.soridam.domain.rewarditem_service.domain.Good;
import sorisoop.soridam.domain.user_service.user.domain.User;

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
	private Good good;

	@Enumerated(STRING)
	@Column(nullable = false)
	private RedemptionStatus status;

	private LocalDateTime redeemedAt;

	public void approve() {
		this.status = APPROVED;
		this.redeemedAt = LocalDateTime.now();
	}

	public void reject() {
		this.status = REJECTED;
	}
}

