package sorisoop.soridam.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
