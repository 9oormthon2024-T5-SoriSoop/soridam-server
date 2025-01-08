package sorisoop.soridam.domain.noise.application;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseRepository;
import sorisoop.soridam.domain.noise.exception.NoiseNotFoundException;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class NoiseCommandService {
	private final NoiseRepository noiseRepository;
	private final GeometryUtils geometryUtils;
	private final UserQueryService userQueryService;

	public Noise createNoise(double x, double y, int maxDecibel, int avgDecibel, String review) {
		User user = userQueryService.getById("qnpranpswnps12@");
		Point point = geometryUtils.createPoint(x, y);
		Noise noise = Noise.create(user, point, maxDecibel, avgDecibel, review);

		return noiseRepository.save(noise);
	}

	public void deleteNoise(String id) {
		if (!noiseRepository.existsById(id)) {
			throw new NoiseNotFoundException();
		}
		noiseRepository.deleteById(id);
	}
}
