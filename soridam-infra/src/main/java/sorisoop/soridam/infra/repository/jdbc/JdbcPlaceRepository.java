package sorisoop.soridam.infra.repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.dto.PlaceInsertDto;

@Repository
@RequiredArgsConstructor
public class JdbcPlaceRepository {

	private final JdbcTemplate jdbcTemplate;

	public void batchInsert(List<PlaceInsertDto> places) {
		if (places == null || places.isEmpty()) return;

		String sql = """
			INSERT INTO place (
				location, road_address, region_address, category, place_name, place_url, created_at
			) VALUES (?, ?, ?, ?, ?, ?)
		""";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				PlaceInsertDto place = places.get(i);
				ps.setObject(1, place.location());
				ps.setString(2, place.roadAddress());
				ps.setString(3, place.regionAddress());
				ps.setString(4, place.category().name());
				ps.setString(5, place.placeName());
				ps.setString(6, place.placeUrl());
				ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
			}

			@Override
			public int getBatchSize() {
				return places.size();
			}
		});
	}
}

