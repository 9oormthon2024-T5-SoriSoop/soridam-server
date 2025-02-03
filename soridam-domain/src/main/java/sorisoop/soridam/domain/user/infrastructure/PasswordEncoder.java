package sorisoop.soridam.domain.user.infrastructure;

public interface PasswordEncoder {
	String encode(String rawPassword);
	boolean matches(String rawPassword, String encodedPassword);
}
