package com.example.cinema.controller

import com.example.cinema.configuration.rest.throwServiceException
import com.example.cinema.controller.api.RatingApi
import com.example.cinema.infrastructure.service.MovieService
import com.example.cinema.infrastructure.service.RatingService
import com.example.cinema.model.entity.RatingEntity
import com.example.cinema.model.request.RateMovieRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RatingController(val ratingService: RatingService, val movieService: MovieService) : RatingApi {

    override fun submitReview(@RequestBody rateMovieRequest: RateMovieRequest) {
        movieService.getMovie(rateMovieRequest.movieId)
            .orElse(null)
            ?.let { movieEntity ->
                ratingService.saveRating(
                    RatingEntity(
                        ratingId = null,
                        movie = movieEntity,
                        rating = rateMovieRequest.rating
                    )
                )
            } ?: throwServiceException(HttpStatus.NOT_FOUND, "Movie you want to rate does not exist")
    }

}