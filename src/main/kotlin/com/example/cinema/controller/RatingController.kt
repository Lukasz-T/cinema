package com.example.cinema.controller

import com.example.cinema.configuration.exception.throwServiceException
import com.example.cinema.controller.api.RatingApi
import com.example.cinema.infrastructure.service.MovieService
import com.example.cinema.infrastructure.service.RatingService
import com.example.cinema.model.dto.MovieRatingDto
import com.example.cinema.model.entity.RatingEntity
import com.example.cinema.model.request.RateMovieRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class RatingController(private val ratingService: RatingService, private val movieService: MovieService) : RatingApi {

    override fun submitRating(@RequestBody rateMovieRequest: RateMovieRequest): ResponseEntity<Any> {
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
                return ResponseEntity.ok("Rating submitted successfully")
            } ?: throwServiceException(HttpStatus.NOT_FOUND, "Movie you want to rate does not exist")
    }

    override fun getRatingForMoview(movieId: Long): ResponseEntity<MovieRatingDto> {
        val averageRating: BigDecimal
        val movieTitle: String?

        movieService.getMovie(movieId)
            .orElse(null)
            ?.let { movieEntity ->
                movieTitle = movieEntity.title;
                averageRating = movieEntity.ratings
                    ?.map { it.rating }
                    ?.average()?.toBigDecimal()!!

                return ResponseEntity.ok(
                    MovieRatingDto(
                        title = movieTitle,
                        rating = averageRating
                    )
                )
            } ?: throwServiceException(HttpStatus.NOT_FOUND, "Movie doesn't exist")

    }
}