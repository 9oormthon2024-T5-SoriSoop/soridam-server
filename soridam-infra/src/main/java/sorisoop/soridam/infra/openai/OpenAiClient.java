package sorisoop.soridam.infra.openai;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class OpenAiClient {
	private final WebClient webClient;

	@Value("${openai.api.url}")
	private String apiUrl;

	@Value("${openai.api.key}")
	private String apiKey;

	public String requestChatCompletion(String prompt) {
		OpenAiRequest request = OpenAiRequest.of(prompt);

		return webClient.post()
			.uri(apiUrl)
			.header("Authorization", "Bearer " + apiKey)
			.bodyValue(request)
			.retrieve()
			.onStatus(
				status -> status.is4xxClientError() || status.is5xxServerError(),
				clientResponse -> clientResponse.bodyToMono(String.class)
					.map(body -> new RuntimeException("OpenAI API 에러: " + body))
			)
			.bodyToMono(OpenAiResponse.class)
			.map(OpenAiResponse::extractMessage)
			.block();
	}
}
