package com.example.cinema.util

import com.example.cinema.model.dto.MovieDto
import com.example.cinema.model.dto.MovieShortDto
import com.example.cinema.model.dto.RatingDto
import com.example.cinema.model.dto.ShowtimeDto
import com.example.cinema.model.entity.MovieEntity
import com.example.cinema.model.entity.RatingEntity
import com.example.cinema.model.entity.ShowtimeEntity
import com.example.cinema.model.request.AddShowTimeAndPriceRequest


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
                title = entity.title,
                imdbId = entity.imdbId,
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

        fun toShowTimeDto(entity: ShowtimeEntity): ShowtimeDto {
            return ShowtimeDto(
                startTime = entity.showtime,
                ticketPrice = entity.price
            )
        }

        fun toShowtimeEntity(
            addShowTimeAndPriceRequest: AddShowTimeAndPriceRequest,
            movie: MovieEntity
        ): ShowtimeEntity {
            return ShowtimeEntity(
                null, movie, addShowTimeAndPriceRequest.newTime, addShowTimeAndPriceRequest.newTicketPrice
            )
        }

    }
}