package sorisoop.soridam.domain.reward_service.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	private String description;

	@Column(nullable = false)
	private int pointCost;

	private String imageUrl;

	private Integer stock;

	public void decreaseStock() {
		if (stock != null && stock > 0) {
			this.stock--;
		}
	}

	public static Good create(String name, String description, int pointCost, String imageUrl, Integer stock) {
		return Good.builder()
			.name(name)
			.description(description)
			.pointCost(pointCost)
			.imageUrl(imageUrl)
			.stock(stock)
			.build();
	}
}

