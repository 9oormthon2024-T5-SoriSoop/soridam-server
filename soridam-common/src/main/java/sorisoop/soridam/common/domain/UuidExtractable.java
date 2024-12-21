package sorisoop.soridam.common.domain;

public interface UuidExtractable {
	String getId();

	default String extractUuid() {
		String id = getId();
		return id != null && id.contains("_") ? id.substring(id.indexOf("_") + 1) : null;
	}
}
