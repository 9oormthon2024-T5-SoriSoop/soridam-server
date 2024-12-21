package sorisoop.soridam.common.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;

@Component
public class GeometryUtils {
	private final GeometryFactory geometryFactory;

	public GeometryUtils() {
		this.geometryFactory = new GeometryFactory(new PrecisionModel(), 5181);
	}

	public Point createPoint(double x, double y) {
		return geometryFactory.createPoint(new Coordinate(x, y));
	}
}
