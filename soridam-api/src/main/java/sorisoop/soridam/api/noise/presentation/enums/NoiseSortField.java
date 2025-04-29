package sorisoop.soridam.api.noise.presentation.enums;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoiseSortField {
	AVG_DECIBEL("avgDecibel"),
	ID("id");

	private final String value;

	public Sort toSort(String order) {
		Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		return Sort.by(direction, this.value);
	}
}
