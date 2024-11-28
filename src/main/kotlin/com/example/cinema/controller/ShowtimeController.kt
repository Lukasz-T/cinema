package com.example.cinema.controller

import com.example.cinema.configuration.rest.throwServiceException
import com.example.cinema.controller.api.ShowtimeApi
import com.example.cinema.infrastructure.service.MovieService
import com.example.cinema.infrastructure.service.ShowtimeService
import com.example.cinema.model.dto.ShowtimeDto
import com.example.cinema.model.request.AddShowTimeAndPriceRequest
import com.example.cinema.util.ObjectMapperUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class ShowtimeController(private val showtimeService: ShowtimeService, private val movieService: MovieService) :
    ShowtimeApi {

    override fun getShowtimesForMovie(
        @PathVariable movieId: Long,
        @RequestParam fromTime: LocalDateTime
    ): ResponseEntity<List<ShowtimeDto>> {
        val list = showtimeService.getShowtimesForMovie(movieId, fromTime)
            .filter { it.isNotEmpty() }
            .map { showtimes ->
                showtimes.map { ObjectMapperUtils.toShowTimeDto(it) }
            }
            .orElseThrow {
                throwServiceException(
                    HttpStatus.NOT_FOUND,
                    "Movie you want to find showtimes for does not exist or has no showtimes"
                )
            }
        return ResponseEntity.ok(list)
    }

    override fun saveShowTimeAndPrice(addShowTimeAndPriceRequest: AddShowTimeAndPriceRequest): ResponseEntity<Any> {
        movieService.getMovie(addShowTimeAndPriceRequest.movieId)
            .orElse(null)
            ?.let { movieEntity ->
                showtimeService.saveShowtime(
                    ObjectMapperUtils.toShowtimeEntity(
                        addShowTimeAndPriceRequest,
                        movieEntity
                    )
                )
            } ?: throwServiceException(
            HttpStatus.NOT_FOUND,
            "Movie you want to add new showtime and price to does not exist"
        )
        return ResponseEntity.ok("New showtime and price saved")
    }
}
