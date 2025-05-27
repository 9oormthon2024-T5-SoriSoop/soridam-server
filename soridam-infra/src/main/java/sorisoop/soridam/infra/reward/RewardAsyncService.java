package sorisoop.soridam.infra.reward;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.reward.domain.PointRedemption;
import sorisoop.soridam.domain.reward.domain.UserPoint;
import sorisoop.soridam.domain.reward.repository.PointRedemptionRepository;
import sorisoop.soridam.domain.reward.repository.UserPointRepository;
import sorisoop.soridam.domain.user.user.domain.User;
import sorisoop.soridam.domain.user.user.domain.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class RewardAsyncService {
	private final PointRedemptionRepository pointRedemptionRepository;
	private final UserRepository userRepository;
	private final UserPointRepository userPointRepository;

	@Async
	@Transactional
	public void handleRedemptionRequest(RedemptionRequestedEvent event) {
		try {
			PointRedemption redemption = pointRedemptionRepository.findById(event.redemptionId())
				.orElseThrow(() -> new IllegalStateException("교환 요청이 존재하지 않습니다."));
			User user = userRepository.findById(event.userId())
				.orElseThrow(() -> new IllegalStateException("사용자가 존재하지 않습니다."));

			int requiredPoint = redemption.getRewardItem().getPointCost();

			user.subtractTotalPoint(requiredPoint);

			String reason = "상품 교환: " + redemption.getRewardItem().getName();
			userPointRepository.save(UserPoint.create(user, -requiredPoint, reason));

			redemption.approve("CODE-" + redemption.getId());
			log.info("포인트 교환 승인 완료 - userId={}, redemptionId={}", user.getId(), redemption.getId());

		} catch (Exception e) {
			log.error("포인트 교환 비동기 처리 실패 - redemptionId=" + event.redemptionId(), e);
			// 예외 발생 시 별도 보상 로직
		}
	}
}
