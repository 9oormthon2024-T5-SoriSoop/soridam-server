package com.sorisoop.server.review.service;

import org.springframework.stereotype.Service;

import com.sorisoop.server.review.repository.JpaReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final JpaReviewRepository jpaReviewRepository;
}
