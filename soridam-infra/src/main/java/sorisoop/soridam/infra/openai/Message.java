package sorisoop.soridam.infra.openai;

public record Message(
	String role,
	String content
) {
}
