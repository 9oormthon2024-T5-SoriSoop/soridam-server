package com.sorisoop.server.noise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sorisoop.server.noise.domain.Noise;

public interface JpaNoiseRepository extends JpaRepository<Noise, Long> {
}
