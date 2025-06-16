package sorisoop.soridam.infra.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.like.domain.Like;

public interface JpaLikeRepository extends JpaRepository<Like, Long> {
}
