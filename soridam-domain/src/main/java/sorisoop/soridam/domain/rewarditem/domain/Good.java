package sorisoop.soridam.domain.rewarditem.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.common.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
public class Good extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(STRING)
	private GoodType type;

	private String description;

	@Column(nullable = false)
	private int pointCost;

	private String imageUrl;

	private Integer stock;

	private boolean hidden;

	public void decreaseStock() {
		if (stock != null && stock > 0) {
			this.stock--;
		}
	}

	public void hide() {
		this.hidden = true;
	}

	public void show() {
		this.hidden = false;
	}

	public static Good create(String name, GoodType type, String description, int pointCost, String imageUrl, Integer stock) {
		return Good.builder()
			.name(name)
			.type(type)
			.description(description)
			.pointCost(pointCost)
			.imageUrl(imageUrl)
			.stock(stock)
			.hidden(false)
			.build();
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateType(GoodType type) {
		this.type = type;
	}

	public void updateDescription(String description) {
		this.description = description;
	}

	public void updatePointCost(int pointCost) {
		this.pointCost = pointCost;
	}

	public void updateImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void updateStock(Integer stock) {
		this.stock = stock;
	}
}

