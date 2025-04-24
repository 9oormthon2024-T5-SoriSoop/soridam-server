package sorisoop.soridam.api.address.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.address.application.AddressFacade;
import sorisoop.soridam.api.address.presentation.request.AddressCreateRequest;
import sorisoop.soridam.api.address.presentation.response.AddressPersistResponse;
import sorisoop.soridam.api.address.presentation.response.AddressResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "Address", description = "장소 API")
@RequestMapping("/api/addresses")
public class AddressApiController {
	private final AddressFacade addressFacade;

	@Operation(summary = "id 기반 장소 조회 API", description = """
			- Description : 이 API는 id로 해당 장소를 조회합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> getById(
		@Parameter(description = "조회할 장소의 ID", example = "address-adsfadsf", required = true)
		@PathVariable String id
	) {
		AddressResponse response = addressFacade.getById(id);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "id 기반 장소 조회 API", description = """
			- Description : 이 API는 도로명 주소로 해당 장소를 조회합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping("/road")
	public ResponseEntity<AddressResponse> getByRoadAddress(
		@Parameter(description = "조회할 장소의 ID", example = "충북 청주시 상당구 월평로 189", required = true)
		@RequestParam String roadAddress
	) {
		AddressResponse response = addressFacade.getByRoadAddress(roadAddress);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "장소 데이터 생성 API", description = """
			- Description : 이 API는 장소 데이터를 생성합니다.
		""")
	@ApiResponse(responseCode = "201")
	@PostMapping
	public ResponseEntity<AddressPersistResponse> create(
		@Valid @RequestBody AddressCreateRequest request
	) {
		AddressPersistResponse response = addressFacade.create(request);
		return ResponseEntity.status(CREATED).body(response);
	}
}
