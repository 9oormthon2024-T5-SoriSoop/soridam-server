package sorisoop.soridam.api.goods.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import sorisoop.soridam.domain.rewarditem_service.domain.GoodType;

public record GoodCreateRequest(
	@Schema(description = "상품 이름", example = "스타벅스 아메리카노", requiredMode = REQUIRED)
	@NotBlank
	String name,

	@Schema(description = "상품 타입", example = "GIFTICON", requiredMode = REQUIRED)
	GoodType goodType,

	@Schema(description = "상품 설명", example = "기프티콘 형태의 커피 상품")
	String description,

	@Schema(description = "필요 포인트", example = "1000", requiredMode = REQUIRED)
	@Min(1)
	int pointCost,

	@Schema(description = "이미지 URL", example = "https://image.server.com/item.png")
	String imageUrl,

	@Schema(description = "재고 수량 (null일 경우 무제한)", example = "100")
	@Min(0)
	Integer stock

) {}

