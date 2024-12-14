package com.soridam.server.user.service;

import org.springframework.stereotype.Service;

import com.soridam.server.user.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final JpaUserRepository jpaUserRepository;
}
