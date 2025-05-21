package sorisoop.soridam.api.noise.application;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.common.SortDirection;
import sorisoop.soridam.api.noise.presentation.enums.NoiseLevel;
import sorisoop.soridam.api.noise.presentation.enums.NoiseSortField;
import sorisoop.soridam.api.noise.presentation.request.NoiseCreateRequest;
import sorisoop.soridam.api.noise.presentation.response.NoiseListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoisePersistResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.common.response.SliceResponse;
import sorisoop.soridam.domain.place_service.place.application.PlaceQueryService;
import sorisoop.soridam.domain.place_service.place.domain.Place;
import sorisoop.soridam.domain.place_service.noise.application.NoiseCommandService;
import sorisoop.soridam.domain.place_service.noise.application.NoiseQueryService;
import sorisoop.soridam.domain.place_service.noise.domain.Noise;
import sorisoop.soridam.domain.user_service.user.application.UserQueryService;
import sorisoop.soridam.domain.user_service.user.domain.User;

@Component
@RequiredArgsConstructor
public class NoiseFacade {
	private final NoiseCommandService noiseCommandService;
	private final NoiseQueryService noiseQueryService;
	private final UserQueryService userQueryService;
	private final PlaceQueryService placeQueryService;

	@Transactional(readOnly = true)
	public SliceResponse<NoiseSummaryResponse> getByPlaceWithCursorAndAvgDecibelRange(
		Long placeId,
		String lastValue,
		int limit,
		NoiseLevel level,
		NoiseSortField sort,
		SortDirection order
	) {
		Sort sortSpec = Sort.by(order.toSpringSortDirection(), sort.getValue());

		int minDecibel = (level != null) ? level.getMinDecibel() : 0;
		int maxDecibel = (level != null) ? level.getMaxDecibel() : 120;

		List<Noise> noises = noiseQueryService.getByPlaceWithCursorAndAvgDecibelRange(placeId, lastValue,
			minDecibel, maxDecibel, limit + 1, sortSpec);

		boolean hasNext = noises.size() > limit;
		if (hasNext) {
			noises = noises.subList(0, limit);
		}

		List<NoiseSummaryResponse> responses = noises.stream()
			.map(NoiseSummaryResponse::from)
			.toList();

		String newLastCursor = noises.isEmpty()
			? null
			: extractCursorValue(responses.get(responses.size() - 1), sortSpec);

		return SliceResponse.of(responses, newLastCursor, hasNext);
	}

	private String extractCursorValue(NoiseSummaryResponse response, Sort sort) {
		String sortProperty = sort.stream().findFirst().orElseThrow().getProperty();
		return switch (sortProperty) {
			case "id" -> response.id().toString();
			case "avgDecibel" -> String.valueOf(response.avgDecibel());
			default -> throw new IllegalArgumentException("지원하지 않는 정렬 기준입니다: " + sortProperty);
		};
	}


	@Transactional(readOnly = true)
	public NoiseResponse getNoise(Long id) {
		Noise noise = noiseQueryService.getById(id);
		return NoiseResponse.from(noise);
	}

	@Transactional
	public NoisePersistResponse createNoise(NoiseCreateRequest request) {
		User user = userQueryService.me();
		Place place = placeQueryService.getById(request.addressId());

		Noise noise = noiseCommandService.createNoise(
			user,
			place,
			request.maxDecibel(),
			request.avgDecibel()
		);

		return NoisePersistResponse.from(noise);
	}

	@Transactional
	public void deleteNoise(Long id) {
		User user = userQueryService.me();
		noiseCommandService.deleteNoise(user, id);
	}

	@Transactional(readOnly = true)
	public NoiseListResponse getNoisesByUserId(Long userId) {
		List<Noise> noises = noiseQueryService.getNoisesByUserId(userId);
		List<NoiseResponse> responses = noises.stream()
			.map(NoiseResponse::from)
			.toList();

		return NoiseListResponse.of(responses);
	}
}
