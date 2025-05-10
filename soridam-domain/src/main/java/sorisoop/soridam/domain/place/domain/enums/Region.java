package sorisoop.soridam.domain.place.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Region {
	CHEONGJU("청주", 127.45, 36.60),
	SEOUL("서울", 126.978, 37.5665),
	BUSAN("부산", 129.0756, 35.1796),
	DAEGU("대구", 128.6014, 35.8714),
	INCHEON("인천", 126.7052, 37.4563),
	GWANGJU("광주", 126.853, 35.1595),
	DAEJEON("대전", 127.3845, 36.3504),
	ULSAN("울산", 129.3114, 35.5384),
	JEJU("제주", 126.5312, 33.4996);

	private final String name;
	private final double x; // longitude
	private final double y; // latitude
}
