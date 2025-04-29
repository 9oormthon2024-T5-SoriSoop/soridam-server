package sorisoop.soridam.api.common;

import org.springframework.data.domain.Sort;

public enum SortDirection {
	ASC,
	DESC;

	public Sort.Direction toSpringSortDirection() {
		return this == ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
	}
}

