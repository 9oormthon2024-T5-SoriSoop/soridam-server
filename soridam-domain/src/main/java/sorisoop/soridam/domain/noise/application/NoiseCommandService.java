package sorisoop.soridam.domain.noise.application;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseRepository;
import sorisoop.soridam.domain.noise.exception.NoiseNotFoundException;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.exception.InvalidUserException;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;
import sorisoop.soridam.globalutil.user.UserUtil;

@Service
@Transactional
@RequiredArgsConstructor
public class NoiseCommandService {
	private final NoiseRepository noiseRepository;
	private final GeometryUtils geometryUtils;

	public Noise createNoise(User user, double x, double y, int maxDecibel, int avgDecibel, String review) {
		Point point = geometryUtils.createPoint(x, y);
		Noise noise = Noise.create(user, point, maxDecibel, avgDecibel, review);

		return noiseRepository.save(noise);
	}

	public void deleteNoise(User user, String id) {
		Noise noise = noiseRepository.findById(id)
			.orElseThrow(NoiseNotFoundException::new);

		validateUser(user.getId(), noise.getUser().getId());

		noiseRepository.delete(noise);
	}

	private void validateUser(String user1, String user2) {
		if (!UserUtil.isSameUser(user1, user2)) {
			throw new InvalidUserException();
		}
	}
}
