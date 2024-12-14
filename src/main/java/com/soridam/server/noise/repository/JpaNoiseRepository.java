package com.soridam.server.noise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soridam.server.noise.domain.Noise;

public interface JpaNoiseRepository extends JpaRepository<Noise, Long> {
}
