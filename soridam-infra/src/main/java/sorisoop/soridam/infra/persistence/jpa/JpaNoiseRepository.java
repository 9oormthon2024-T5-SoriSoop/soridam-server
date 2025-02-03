package sorisoop.soridam.infra.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.noise.domain.Noise;

public interface JpaNoiseRepository extends JpaRepository<Noise, String> {
}
