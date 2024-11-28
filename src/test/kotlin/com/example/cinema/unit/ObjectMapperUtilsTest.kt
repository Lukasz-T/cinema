package com.example.cinema.unit

import com.example.cinema.model.entity.MovieEntity
import com.example.cinema.model.entity.RatingEntity
import com.example.cinema.model.entity.ShowtimeEntity
import com.example.cinema.model.request.AddShowTimeAndPriceRequest
import com.example.cinema.util.ObjectMapperUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class ObjectMapperUtilsTest {

    @Test
    fun `toShortDto should convert MovieEntity to MovieShortDto`() {
        val movieEntity = MovieEntity(
            movieId = 1L,
            title = "THE FAST AND THE FURIOUS",
            imdbId = "tt0232500",
            showtimes = listOf(
                ShowtimeEntity(
                    showtimeId = 1L,
                    movie = MovieEntity(movieId = 1L),
                    showtime = LocalDateTime.of(2024, 11, 28, 18, 0),
                    price = BigDecimal("15.00")
                )
            ),
            ratings = emptyList()
        )

        val result = ObjectMapperUtils.toShortDto(movieEntity)

        assertNotNull(result)
        assertEquals("THE FAST AND THE FURIOUS", result.title)
        result.showings?.let { assertEquals(1, it.size) }
        assertEquals(LocalDateTime.of(2024, 11, 28, 18, 0), result.showings?.get(0)?.startTime)
        assertEquals(BigDecimal("15.00"), result.showings?.get(0)?.ticketPrice)
    }

    @Test
    fun `toMovieDto should convert MovieEntity to MovieDto`() {
        val movieEntity = MovieEntity(
            movieId = 1L,
            title = "THE FAST AND THE FURIOUS",
            imdbId = "tt0232500",
            showtimes = listOf(
                ShowtimeEntity(
                    showtimeId = 1L,
                    movie = MovieEntity(movieId = 1L),
                    showtime = LocalDateTime.of(2024, 11, 28, 18, 0),
                    price = BigDecimal("15.00")
                )
            ),
            ratings = listOf(
                RatingEntity(
                    ratingId = 1L,
                    movie = MovieEntity(movieId = 1L),
                    rating = 8
                )
            )
        )

        val result = ObjectMapperUtils.toMovieDto(movieEntity)

        assertNotNull(result)
        assertEquals("THE FAST AND THE FURIOUS", result.title)
        assertEquals("tt0232500", result.imdbId)
        assertEquals(1, result.showings?.size)
        assertEquals(LocalDateTime.of(2024, 11, 28, 18, 0), result.showings?.get(0)?.startTime)
        assertEquals(BigDecimal("15.00"), result.showings?.get(0)?.ticketPrice)
        assertEquals(1, result.ratings?.size)
        assertEquals(8, result.ratings?.get(0)?.value)
    }

    @Test
    fun `toShowtimeDto should convert ShowtimeEntity to ShowtimeDto`() {
        val showtimeEntity = ShowtimeEntity(
            showtimeId = 1L,
            movie = MovieEntity(movieId = 1L),
            showtime = LocalDateTime.of(2024, 11, 28, 18, 0),
            price = BigDecimal("15.00")
        )

        val result = ObjectMapperUtils.toShowtimeDto(showtimeEntity)

        assertNotNull(result)
        assertEquals(LocalDateTime.of(2024, 11, 28, 18, 0), result.startTime)
        assertEquals(BigDecimal("15.00"), result.ticketPrice)
    }

    @Test
    fun `toRatingDto should convert RatingEntity to RatingDto`() {
        val ratingEntity = RatingEntity(
            ratingId = 1L,
            movie = MovieEntity(movieId = 1L),
            rating = 8
        )

        val result = ObjectMapperUtils.toRatingDto(ratingEntity)

        assertNotNull(result)
        assertEquals(8, result.value)
    }

    @Test
    fun `toShowtimeEntity should convert AddShowTimeAndPriceRequest to ShowtimeEntity`() {
        val movieEntity = MovieEntity(
            movieId = 1L,
            title = "THE FAST AND THE FURIOUS",
            imdbId = "tt0232500",
            showtimes = listOf(),
            ratings = listOf()
        )
        val request = AddShowTimeAndPriceRequest(
            newTime = LocalDateTime.of(2024, 11, 28, 18, 0),
            newTicketPrice = BigDecimal("15.00"),
            movieId = 1,
        )

        val result = ObjectMapperUtils.toShowtimeEntity(request, movieEntity)

        assertNotNull(result)
        assertEquals(movieEntity, result.movie)
        assertEquals(LocalDateTime.of(2024, 11, 28, 18, 0), result.showtime)
        assertEquals(BigDecimal("15.00"), result.price)
    }
}
