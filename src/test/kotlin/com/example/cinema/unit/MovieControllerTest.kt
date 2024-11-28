package com.example.cinema.unit


import com.example.cinema.controller.MovieController
import com.example.cinema.infrastructure.service.MovieService
import com.example.cinema.model.dto.MovieShortDto
import com.example.cinema.model.dto.OmdbDto
import com.example.cinema.model.dto.OmdbRatingsDto
import com.example.cinema.model.dto.ShowtimeDto
import com.example.cinema.model.entity.MovieEntity
import com.example.cinema.model.entity.RatingEntity
import com.example.cinema.model.entity.ShowtimeEntity
import com.example.cinema.util.ObjectMapperUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class MovieControllerTest {

    @Mock
    private lateinit var movieService: MovieService

    @InjectMocks
    private lateinit var movieController: MovieController

    @Test
    fun `getAllMovies should return list of MovieShortDto`() {
        // Mock data
        val movieEntities = listOf(
            MovieEntity(
                movieId = 1L,
                title = "Movie 1",
                imdbId = "tt1234567",
                showtimes = listOf(
                    ShowtimeEntity(
                        showtimeId = 1L,
                        movie = MovieEntity(movieId = 1L),
                        showtime = LocalDateTime.of(2024, 11, 30, 18, 0),
                        price = BigDecimal("12.50")
                    )
                ),
                ratings = listOf(
                    RatingEntity(
                        ratingId = 1L,
                        movie = MovieEntity(movieId = 1L),
                        rating = 8
                    )
                )
            ),
            MovieEntity(
                movieId = 2L,
                title = "Movie 2",
                imdbId = "tt2345678",
                showtimes = listOf(
                    ShowtimeEntity(
                        showtimeId = 2L,
                        movie = MovieEntity(movieId = 2L),
                        showtime = LocalDateTime.of(2024, 12, 1, 20, 0),
                        price = BigDecimal("15.00")
                    )
                ),
                ratings = listOf(
                    RatingEntity(
                        ratingId = 2L,
                        movie = MovieEntity(movieId = 2L),
                        rating = 7
                    )
                )
            )
        )

        val movieShortDtos = listOf(
            MovieShortDto(
                title = "Movie 1",
                showings = listOf(
                    ShowtimeDto(
                        startTime = LocalDateTime.of(2024, 11, 30, 18, 0),
                        ticketPrice = BigDecimal("12.50")
                    )
                )
            ),
            MovieShortDto(
                title = "Movie 2",
                showings = listOf(
                    ShowtimeDto(
                        startTime = LocalDateTime.of(2024, 12, 1, 20, 0),
                        ticketPrice = BigDecimal("15.00")
                    )
                )
            )
        )

        // Mock service behavior
        `when`(movieService.getMovieList()).thenReturn(movieEntities)

        // Mock utility method
        mockk<ObjectMapperUtils.Companion> {
            every { toShortDto(movieEntities[0]) } returns movieShortDtos[0]
            every { toShortDto(movieEntities[1]) } returns movieShortDtos[1]
        }

        // Call the method
        val response = movieController.getAllMovies()

        // Assertions
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(movieShortDtos, response.body)
    }

    @Test
    fun `getMovieDetails should return OmdbDto for given movieId`() {
        // Mock data
        val movieId = 1L
        val omdbDto = OmdbDto(
            title = "The Fast and the Furious",
            year = "2001",
            poster = "https://m.media-amazon.com/images/I/51dAOTGGs+L._AC_.jpg",
            imdbID = "tt0232500",
            type = "movie",
            rated = "PG-13",
            released = "2001-06-22",
            runtime = "106 min",
            genre = "Action, Crime, Thriller",
            director = "Rob Cohen",
            writer = "Gary Scott Thompson, Erik Bergquist, David Ayer",
            actors = "Vin Diesel, Paul Walker, Michelle Rodriguez, Jordana Brewster",
            plot = "A street racer is recruited by the FBI to infiltrate a dangerous gang of car hijackers...",
            language = "English",
            country = "USA",
            awards = "1 win & 8 nominations",
            metascore = "66",
            imdbRating = "6.8",
            imdbVotes = "243,000",
            response = "True",
            dvd = "2001-10-30",
            boxOffice = "$207,283,925",
            production = "Universal Pictures",
            webSite = "https://www.universalpictures.com/movies/the-fast-and-the-furious",
            ratings = mutableListOf(OmdbRatingsDto("Internet Movie Database", "6.8/10"))
        )

        // Mock service behavior
        `when`(movieService.getMovieDetails(movieId)).thenReturn(omdbDto)

        // Call the method
        val response = movieController.getMovieDetails(movieId)

        // Assertions
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(omdbDto, response.body)
    }
}
