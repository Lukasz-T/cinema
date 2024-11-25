package com.example.cinema.infrastructure.service

import com.example.cinema.infrastructure.repository.ReviewRepository
import com.example.cinema.model.entity.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewService(@Autowired val reviewRepository: ReviewRepository) {
    fun getReviewsForMovie(movieId: Long): List<Review> {
        return reviewRepository.findByMovieMovieId(movieId)
    }

    fun saveReview(review: Review): Review {
        return reviewRepository.save(review)
    }
}