package sorisoop.soridam.api.address.presentation.response;

import java.util.List;

import lombok.Builder;

@Builder
public record AddressListResponse(
	List<AddressResponse> responses
) {
	public static AddressListResponse of(List<AddressResponse> responses) {
		return AddressListResponse.builder()
			.responses(responses)
			.build();
	}
}
