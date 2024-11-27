package com.example.cinema.util

import com.example.cinema.model.dto.MovieDto
import com.example.cinema.model.dto.MovieShortDto
import com.example.cinema.model.dto.RatingDto
import com.example.cinema.model.dto.ShowtimeDto
import com.example.cinema.model.entity.MovieEntity
import com.example.cinema.model.entity.RatingEntity
import com.example.cinema.model.entity.ShowtimeEntity


class ObjectMapperUtils() {
    companion object {
        fun toShortDto(movie: MovieEntity): MovieShortDto {
            return MovieShortDto(
                title = movie.title,
                showings = movie.showtimes?.map { toShowingDto(it) } ?: emptyList()
            )
        }

        private fun toShowingDto(showtime: ShowtimeEntity): ShowtimeDto {
            return ShowtimeDto(
                startTime = showtime.showtime,
                ticketPrice = showtime.price
            )
        }

        fun toMovieDto(entity: MovieEntity): MovieDto {
            return MovieDto(
                movieId = entity.movieId ?: 0L,
                title = entity.title ?: "Untitled",
                imdbId = entity.imdbId ?: "Unknown",
                showings = entity.showtimes?.map { toShowtimeDto(it) },
                ratings = entity.ratings?.map { toRatingDto(it) }
            )
        }

        private fun toShowtimeDto(entity: ShowtimeEntity): ShowtimeDto {
            return ShowtimeDto(
                startTime = entity.showtime,
                ticketPrice = entity.price
            )
        }

        private fun toRatingDto(entity: RatingEntity): RatingDto {
            return RatingDto(
                value = entity.rating
            )
        }

    }
}