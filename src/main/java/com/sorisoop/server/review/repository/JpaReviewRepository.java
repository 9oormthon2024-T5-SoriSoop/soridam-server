package com.sorisoop.server.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sorisoop.server.review.domain.Review;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
}
