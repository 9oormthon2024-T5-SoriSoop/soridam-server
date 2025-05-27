package sorisoop.soridam.domain.rewarditem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GoodType {
	GIFTICON("기프티콘"),
	COUPON("할인쿠폰"),
	ETC("기타");

	private final String description;
}
