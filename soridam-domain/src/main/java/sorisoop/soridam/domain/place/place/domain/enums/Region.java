package sorisoop.soridam.domain.place.place.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Region {
	// 수도권
	SEOUL("서울특별시", 126.9780, 37.5665),
	INCHEON("인천광역시", 126.7052, 37.4563),
	GYEONGGI("경기도", 127.5183, 37.4138),

	// 강원도
	GANGWON("강원특별자치도", 128.3115, 37.8228),

	// 충청도
	CHUNGBUK("충청북도", 127.4914, 36.6357),
	CHUNGNAM("충청남도", 126.8454, 36.5184),
	DAEJEON("대전광역시", 127.3845, 36.3504),
	SEJONG("세종특별자치시", 127.2891, 36.4801),

	// 전라도
	JEONBUK("전라북도", 127.1088, 35.8203),
	JEONNAM("전라남도", 126.4632, 34.8161),
	GWANGJU("광주광역시", 126.8530, 35.1595),

	// 경상도
	GYEONGBUK("경상북도", 128.8889, 36.4919),
	GYEONGNAM("경상남도", 128.2132, 35.2383),
	DAEGU("대구광역시", 128.6014, 35.8714),
	BUSAN("부산광역시", 129.0756, 35.1796),
	ULSAN("울산광역시", 129.3114, 35.5384),

	// 제주
	JEJU("제주특별자치도", 126.5312, 33.4996);

	private final String name;
	private final double x; // longitude
	private final double y; // latitude
}

