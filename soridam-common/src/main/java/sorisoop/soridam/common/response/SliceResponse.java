package sorisoop.soridam.common.response;

import java.util.List;

import lombok.Builder;

@Builder
public record SliceResponse<T>(
	List<T> contents,
	Long lastId,
	boolean hasNext
) {
	public static <T> SliceResponse<T> of(List<T> contents, Long lastId, boolean hasNext) {
		return SliceResponse.<T>builder()
			.contents(contents)
			.lastId(lastId)
			.hasNext(hasNext)
			.build();
	}
}
