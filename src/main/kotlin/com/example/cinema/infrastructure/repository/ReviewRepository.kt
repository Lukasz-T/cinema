package com.example.cinema.infrastructure.repository

import com.example.cinema.model.entity.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {
    fun findByMovieMovieId(movieId: Long): List<Review>
}