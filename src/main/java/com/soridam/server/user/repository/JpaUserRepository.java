package com.soridam.server.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soridam.server.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
