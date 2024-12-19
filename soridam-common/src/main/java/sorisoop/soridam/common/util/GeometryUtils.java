package sorisoop.soridam.common.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GeometryUtils {
	private final GeometryFactory geometryFactory;

	public Point createPoint(double x, double y) {
		return geometryFactory.createPoint(new Coordinate(x, y));
	}
}
