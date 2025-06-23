package sorisoop.soridam.domain.place.place.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import lombok.Builder;
import lombok.Getter;
import sorisoop.soridam.domain.place.place.dto.PlaceInsertDto;

@Getter
@Builder
@Document(indexName = "places")
@Setting(settingPath = "/elasticsearch/places-settings.json")
public class PlaceDocument {

	@Id
	private String id;

	@Field(type = FieldType.Double)
	private double x;

	@Field(type = FieldType.Double)
	private double y;

	@Field(type = FieldType.Text, analyzer = "place_description_analyzer")
	private String roadAddress;

	@MultiField(
		mainField = @Field(type = FieldType.Text, analyzer = "place_description_analyzer"),
		otherFields = {
			@InnerField(suffix = "raw", type = FieldType.Keyword)
		}
	)
	private String regionAddress;


	@Field(type = FieldType.Keyword)
	private String category;

	@MultiField(
		mainField = @Field(type = FieldType.Text),
		otherFields = {
			@InnerField(suffix = "raw", type = FieldType.Keyword)
		}
	)
	private String categoryDescription;

	@MultiField(
		mainField = @Field(type = FieldType.Text, analyzer = "place_name_analyzer"),
		otherFields = {
			@InnerField(suffix = "raw", type = FieldType.Keyword)
		}
	)
	private String placeName;

	@Field(type = FieldType.Text)
	private String placeUrl;

	public static PlaceDocument from(PlaceInsertDto dto) {
		return PlaceDocument.builder()
			.id(dto.generateKey())
			.x(dto.location().getX())
			.y(dto.location().getY())
			.roadAddress(dto.roadAddress())
			.regionAddress(dto.regionAddress())
			.category(dto.category().name())
			.categoryDescription(dto.category().getDescription())
			.placeName(dto.placeName())
			.placeUrl(dto.placeUrl())
			.build();
	}
}
