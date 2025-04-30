package sorisoop.soridam.infra.openai;

import java.util.List;

import lombok.Builder;

@Builder
public record OpenAiRequest(
	String model,
	List<Message> messages
) {
	public static OpenAiRequest of(String prompt) {
		return OpenAiRequest.builder()
			.model("gpt-3.5-turbo")
			.messages(List.of(new Message("user", prompt)))
			.build();
	}
}
