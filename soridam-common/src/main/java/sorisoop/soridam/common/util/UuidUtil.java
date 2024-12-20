package sorisoop.soridam.common.util;

import org.springframework.stereotype.Component;

@Component
public class UuidUtil {

	public String extractUuid(String id) {
		if (id == null || !id.contains("_")) {
			throw new IllegalArgumentException("Invalid ID format");
		}
		return id.substring(id.indexOf("_") + 1);
	}
}
