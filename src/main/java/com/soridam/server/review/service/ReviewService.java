package com.soridam.server.review.service;

import org.springframework.stereotype.Service;

import com.soridam.server.review.repository.JpaReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final JpaReviewRepository jpaReviewRepository;
}
