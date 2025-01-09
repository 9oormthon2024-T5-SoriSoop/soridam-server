package sorisoop.soridam.globalutil.user;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {
	public static boolean isSameUser(String user1, String user2) {
		if (user1 == null || user2 == null) {
			return false;
		}
		return user1.equals(user2);
	}
}
