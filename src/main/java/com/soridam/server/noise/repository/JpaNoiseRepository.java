package com.soridam.server.noise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soridam.server.noise.domain.Noise;

public interface JpaNoiseRepository extends JpaRepository<Noise, Long> {
	List<Noise> findByUserId(Long userId);
}
