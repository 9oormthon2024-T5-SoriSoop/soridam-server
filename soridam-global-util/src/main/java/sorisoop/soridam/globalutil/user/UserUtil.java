package sorisoop.soridam.globalutil.user;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {
	public static boolean isSameUser(Long user1, Long user2) {
		if (user1 == null || user2 == null) {
			return true;
		}
		return !user1.equals(user2);
	}
}
