package com.soridam.server.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soridam.server.review.domain.Review;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
}
