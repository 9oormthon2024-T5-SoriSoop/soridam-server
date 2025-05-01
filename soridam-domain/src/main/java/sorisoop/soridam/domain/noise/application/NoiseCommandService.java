package sorisoop.soridam.domain.noise.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.domain.Place;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseRepository;
import sorisoop.soridam.domain.noise.exception.NoiseNotFoundException;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.exception.InvalidUserException;
import sorisoop.soridam.globalutil.user.UserUtil;

@Service
@RequiredArgsConstructor
public class NoiseCommandService {
	private final NoiseRepository noiseRepository;

	public Noise createNoise(User user, Place place, int maxDecibel, int avgDecibel) {
		Noise noise = Noise.create(user, place, maxDecibel, avgDecibel);
		return noiseRepository.save(noise);
	}

	public void deleteNoise(User user, Long id) {
		Noise noise = noiseRepository.findById(id)
			.orElseThrow(NoiseNotFoundException::new);

		validateUser(user.getId(), noise.getUser().getId());

		noiseRepository.delete(noise);
	}

	private void validateUser(Long user1, Long user2) {
		if (!UserUtil.isSameUser(user1, user2)) {
			throw new InvalidUserException();
		}
	}
}
