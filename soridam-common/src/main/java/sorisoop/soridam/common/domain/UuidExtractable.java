package sorisoop.soridam.common.domain;

public interface UuidExtractable {
	String getId();

	default String extractUuid() {
		String id = getId();
		return id != null && id.contains("-") ? id.substring(id.indexOf("-") + 1) : null;
	}
}
