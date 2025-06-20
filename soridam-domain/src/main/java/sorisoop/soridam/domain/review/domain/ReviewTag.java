package sorisoop.soridam.domain.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewTag {

	// 분위기 관련
	QUIET("조용함"),
	COZY("아늑함"),
	MODERN("세련됨"),
	MOODY("분위기 좋음"),
	PRIVATE("프라이버시 좋음"),

	// 공간 관련
	CLEAN("청결함"),
	SPACIOUS("넓음"),
	COMFORTABLE_SEAT("좌석이 편함"),
	GOOD_LIGHTING("조명 좋음"),

	// 서비스 관련
	FRIENDLY("친절한 서비스"),
	FAST_SERVICE("응대 빠름"),

	// 활용 목적
	GOOD_FOR_STUDY("공부하기 좋음"),
	GOOD_FOR_WORK("노트북 작업에 적합"),
	GOOD_FOR_CHAT("대화하기 좋음"),
	DATE_SPOT("데이트 장소로 좋음"),

	// 접근성/편의성
	ACCESSIBLE("찾기 쉬움"),
	CONVENIENT_LOCATION("위치가 편리함"),
	QUIET_MORNING("아침에 조용함"),
	QUIET_EVENING("저녁에 조용함");

	private final String description;
}

