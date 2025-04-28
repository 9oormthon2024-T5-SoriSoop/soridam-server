package sorisoop.soridam.api.address.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sorisoop.soridam.domain.address.domain.Address;

@Builder
public record AddressResponse(
	@Schema(description = "X 좌표 (경도)", example = "126.9780", requiredMode = REQUIRED)
	double x,

	@Schema(description = "Y 좌표 (위도)", example = "37.5665", requiredMode = REQUIRED)
	double y,

	@Schema(description = "도로명 주소", example = "서울특별시 동대문구 장한로 110 (장안동)", requiredMode = REQUIRED)
	@NotNull
	String roadAddress,

	@Schema(description = "지번 주소", example = "서울특별시 동대문구 장안동 366-7", requiredMode = REQUIRED)
	@NotNull
	String regionAddress,

	@Schema(description = "장소 카테고리", example = "대형마트", requiredMode = REQUIRED)
	String category
) {
	public static AddressResponse from(Address address) {
		return AddressResponse.builder()
			.x(address.getLocation().getX())
			.y(address.getLocation().getY())
			.roadAddress(address.getRoadAddress())
			.regionAddress(address.getRegionAddress())
			.category(address.getCategory() == null ? null : address.getCategory().getDescription())
			.build();
	}
}
