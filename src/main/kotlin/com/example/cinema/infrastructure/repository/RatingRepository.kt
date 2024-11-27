package com.example.cinema.infrastructure.repository

import com.example.cinema.model.entity.RatingEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RatingRepository : JpaRepository<RatingEntity, Long> {
    fun findByMovieMovieId(movieId: Long): List<RatingEntity>
}