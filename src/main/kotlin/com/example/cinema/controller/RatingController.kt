package com.example.cinema.controller

import com.example.cinema.controller.api.RatingApi
import com.example.cinema.infrastructure.service.RatingService
import com.example.cinema.model.entity.MovieEntity
import com.example.cinema.model.entity.RatingEntity
import com.example.cinema.model.request.RateMovie
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RatingController(var ratingService: RatingService) : RatingApi {

    override fun submitReview(@RequestBody rateMovie: RateMovie) {
        val ratingEntity: RatingEntity? =
            rateMovie.rating?.let { RatingEntity(null, MovieEntity(rateMovie.movieId), it) }
        if (ratingEntity != null) {
            ratingService.saveRating(ratingEntity)
        }
    }

}