package sorisoop.soridam.api.address.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.address.domain.Address;

@Builder
public record AddressPersistResponse(
	@Schema(description = "address ID", example = "address-asdfjklsadjklsamlsdfsldm", requiredMode = REQUIRED)
	String id
) {
	public static AddressPersistResponse from(Address address) {
		return AddressPersistResponse.builder()
			.id(address.getId())
			.build();
	}
}
