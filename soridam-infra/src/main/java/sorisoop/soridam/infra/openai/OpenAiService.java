package sorisoop.soridam.infra.openai;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.address.domain.enums.Category;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAiService {
	private final OpenAiClient openAiClient;

	public String summarizeReviews(Address address, List<String> reviews) {
		if (reviews == null || reviews.isEmpty()) return null;

		String prompt = buildPrompt(address, reviews);

		try {
			return openAiClient.requestChatCompletion(prompt);
		} catch (Exception e) {
			log.error("GPT 요약 요청 실패: {}", e.getMessage());
			return "요약을 생성하지 못했습니다.";
		}
	}

	private String buildPrompt(Address address, List<String> reviews) {
		String roadAddress = address.getRoadAddress();
		String categoryText = Optional.ofNullable(address.getCategory())
			.map(Category::getDescription)
			.orElse(null);

		String meta = categoryText != null
			? "이 장소는 %s이며, 위치는 \"%s\"입니다.".formatted(categoryText, roadAddress)
			: "이 장소는 위치가 \"%s\"입니다.".formatted(roadAddress);

		String reviewText = reviews.stream()
			.map(r -> "- " + r)
			.collect(Collectors.joining("\n"));

		return """
        %s
        다음은 이 장소에 대한 사용자 리뷰입니다.
			- 장소 자체에 대한 일반적인 소개는 생략하고,
			- 오직 아래 리뷰들의 내용을 기반으로만 요약해주세요.
			- 요약은 **한 문장**으로 작성해주세요.
			- 문장은 너무 딱딱한 설명체가 아니라, **자연스럽고 일상적인 말투**로 작성해주세요.
			- 반드시 한국어로 작성해주세요.
			
			예시를 참고하여 한가지만 보내주세요.
			- 분위기가 조용해서 집중하기 좋았어요.
			- 늦은 밤에도 안전해서 좋았습니다.
			- 주변이 어두워서 처음엔 찾기 힘들었어요.
        %s
        """.formatted(meta, reviewText);
	}

}
