package com.example.cinema.infrastructure.service

import com.example.cinema.infrastructure.repository.RatingRepository
import com.example.cinema.model.entity.RatingEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RatingService(@Autowired val ratingRepository: RatingRepository) {
    fun getRatingsForMovie(movieId: Long): List<RatingEntity> {
        return ratingRepository.findByMovieMovieId(movieId)
    }

    fun saveRating(ratingEntity: RatingEntity): RatingEntity {
        return ratingRepository.save(ratingEntity)
    }
}