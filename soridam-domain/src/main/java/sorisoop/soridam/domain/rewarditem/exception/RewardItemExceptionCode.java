package sorisoop.soridam.domain.rewarditem.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sorisoop.soridam.common.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum RewardItemExceptionCode implements ExceptionCode {
	REWARD_ITEM_NOT_FOUND(NOT_FOUND, "해당 보상 상품을 찾을 수 없습니다."),
	REWARD_ITEM_OUT_OF_STOCK(BAD_REQUEST, "보상 상품의 수량이 부족합니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
