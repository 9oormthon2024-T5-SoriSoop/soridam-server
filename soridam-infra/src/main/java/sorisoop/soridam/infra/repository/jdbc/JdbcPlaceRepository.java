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

		// ST_GeomFromText 사용 시 문자열 그대로 넘겨야 함 (PGobject 사용하지 않음)
		String sql = "INSERT INTO place (location, roadaddress, regionaddress, category, placename, placeurl, createdat) " +
			"VALUES (ST_GeomFromText(?, 4326), ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				PlaceInsertDto place = places.get(i);

				// POINT(x y) 문자열만 넘긴다. 따옴표는 자동 처리됨.
				String wkt = String.format("POINT(%f %f)",
					place.location().getX(),
					place.location().getY());

				ps.setString(1, wkt);
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
