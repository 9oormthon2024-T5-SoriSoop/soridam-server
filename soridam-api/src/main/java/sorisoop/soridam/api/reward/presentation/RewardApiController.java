package sorisoop.soridam.api.reward.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.reward.application.RewardFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rewards")
@Tag(name = "Reward", description = "보상 API")
public class RewardApiController {
	private final RewardFacade rewardFacade;

	@Operation(summary = "포인트 보상 교환 요청 API", description = """
        - Description : 이 API는 사용자의 포인트로 상품 교환을 요청합니다.
        - 포인트가 부족한 경우 400 에러를 반환합니다.
    """)
	@ApiResponse(responseCode = "201", description = "교환 요청 성공")
	@ApiResponse(responseCode = "400", description = "포인트 부족 또는 잘못된 요청")
	@PostMapping("/rewards/{goodId}")
	public ResponseEntity<PointRedemptionPersistResponse> requestRedemption(
		@PathVariable Long goodId
	) {
		PointRedemptionPersistResponse response = rewardFacade.requestReward(goodId);
		return ResponseEntity.status(CREATED).body(response);
	}

}
