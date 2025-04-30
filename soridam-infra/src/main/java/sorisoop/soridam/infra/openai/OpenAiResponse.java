package sorisoop.soridam.infra.openai;

import java.util.List;

public record OpenAiResponse(
	List<Choice> choices
) {
	public String extractMessage() {
		return choices.get(0).message().content();
	}

	public record Choice(Message message) {}
}
