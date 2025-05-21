package sorisoop.soridam.api.reward.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.reward.RewardFacade;
import sorisoop.soridam.api.reward.presentation.request.GoodCreateRequest;
import sorisoop.soridam.api.reward.presentation.response.GoodListResponse;
import sorisoop.soridam.api.reward.presentation.response.GoodPersistResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reward", description = "보상 API")
@RequestMapping("/api/reward")
public class RewardApiController {

	private final RewardFacade rewardFacade;

	@Operation(summary = "상품 등록 API", description = """
        - Description : 이 API는 새로운 보상 상품을 등록합니다.
        - 관리자 권한이 필요합니다.
    """)
	@ApiResponse(responseCode = "201", description = "상품 생성 성공")
	@PostMapping("/goods")
	public ResponseEntity<GoodPersistResponse> createGood(
		@Valid @RequestBody GoodCreateRequest request
	) {
		GoodPersistResponse response = rewardFacade.createGood(request);
		return ResponseEntity.status(201).body(response);
	}

	@Operation(summary = "상품 숨김 처리 API", description = """
        - Description : 해당 상품을 사용자에게 노출되지 않도록 숨깁니다.
        - 관리자 권한이 필요합니다.
    """)
	@ApiResponse(responseCode = "200", description = "숨김 처리 성공")
	@PostMapping("/goods/{goodId}/hide")
	public ResponseEntity<Void> hideGood(@PathVariable Long goodId) {
		rewardFacade.hideGood(goodId);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "상품 표시 처리 API", description = """
        - Description : 숨김 처리된 상품을 다시 사용자에게 표시합니다.
        - 관리자 권한이 필요합니다.
    """)
	@ApiResponse(responseCode = "200", description = "표시 처리 성공")
	@PostMapping("/goods/{goodId}/show")
	public ResponseEntity<Void> showGood(@PathVariable Long goodId) {
		rewardFacade.showGood(goodId);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "전체 보상 상품 조회 API", description = """
        - Description : 등록된 모든 보상 상품 목록을 조회합니다.
        - 숨겨진 상품도 포함됩니다.
    """)
	@ApiResponse(responseCode = "200", description = "상품 목록 조회 성공")
	@GetMapping("/goods")
	public ResponseEntity<GoodListResponse> getAllGoods() {
		return ResponseEntity.ok(rewardFacade.getAllGoods());
	}
}
