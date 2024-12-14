package com.soridam.server.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.dto.response.NoiseSummaryListResponse;
import com.soridam.server.noise.dto.response.NoiseSummaryResponse;
import com.soridam.server.user.domain.User;
import com.soridam.server.user.exception.UserNotFoundException;
import com.soridam.server.user.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final JpaUserRepository jpaUserRepository;

	@Transactional(readOnly = true)
	public NoiseSummaryListResponse getUserNoises(Long id) {
		User user = jpaUserRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);

		List<Noise> noises = user.getNoises();

		List<NoiseSummaryResponse> responses = noises.stream()
			.map(NoiseSummaryResponse::from)
			.toList();

		return NoiseSummaryListResponse.of(responses);
	}
}
