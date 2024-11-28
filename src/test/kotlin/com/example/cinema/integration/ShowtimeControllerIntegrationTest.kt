package com.example.cinema.integration

import com.example.cinema.infrastructure.repository.MovieRepository
import com.example.cinema.infrastructure.repository.ShowtimeRepository
import com.example.cinema.model.entity.MovieEntity
import com.example.cinema.model.entity.ShowtimeEntity
import com.example.cinema.model.request.AddShowTimeAndPriceRequest
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(
    MethodOrderer.OrderAnnotation::class
)

class ShowtimeControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @Autowired
    private lateinit var showtimeRepository: ShowtimeRepository

    @Test
    @Order(1)
    fun `saveShowTimeAndPrice should successfully save new showtime and price`() {
        // Arrange
        val movieEntity = MovieEntity(
            movieId = 1L,
            title = "The Fast and the Furious 3",
            imdbId = "tt0232502"
        )
        movieRepository.save(movieEntity)

        movieEntity.movieId?.let {
            AddShowTimeAndPriceRequest(
                movieId = it,
                newTicketPrice = BigDecimal("20.00"),
                newTime = LocalDateTime.of(2024, 10, 10, 19, 0)
            )
        }

        // Act and Assert
        mockMvc.perform(
            MockMvcRequestBuilders.post("/showtimes")
                .contentType("application/json")
                .content(
                    """
                    {
                        "movieId": ${movieEntity.movieId},
                        "newTicketPrice": "20.00",
                        "newTime": "2024-10-10 19:00:00"
                    }
                """
                )
        )

        val savedShowtime = showtimeRepository.findAll().find { it.movie.movieId == movieEntity.movieId }
        assert(savedShowtime != null)
        assert(savedShowtime!!.price == BigDecimal("20.00"))
        assert(savedShowtime.showtime == LocalDateTime.of(2024, 10, 10, 19, 0))
    }


    @Test
    fun `saveShowTimeAndPrice should return 404 when movie not found`() {
        // Arrange
        val addShowTimeAndPriceRequest = AddShowTimeAndPriceRequest(
            movieId = 999L, // Non-existent movieId
            newTicketPrice = BigDecimal("20.00"),
            newTime = LocalDateTime.of(2024, 12, 10, 19, 0)
        )

        // Act and Assert
        mockMvc.perform(
            MockMvcRequestBuilders.post("/showtimes")
                .contentType("application/json")
                .content(
                    """
                    {
                        "movieId": ${addShowTimeAndPriceRequest.movieId},
                        "newTicketPrice": "20.00",
                        "newTime": "2024-12-10 19:00:00"
                    }
                """
                )
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `getShowtimesForMovie should return showtimes for a movie`() {
        // Arrange
        val movieEntity = MovieEntity(
            movieId = 1L,
            title = "The Fast and the Furious",
            imdbId = "tt0232500",
            showtimes = listOf(
                ShowtimeEntity(
                    showtimeId = 1L,
                    movie = MovieEntity(movieId = 1L),
                    showtime = LocalDateTime.of(2024, 12, 1, 20, 0),
                    price = BigDecimal("15.00")
                )
            )
        )
        movieRepository.save(movieEntity) // Save the movie to the repository
        showtimeRepository.saveAll(movieEntity.showtimes!!) // Save the showtimes for the movie

        val fromTime = LocalDateTime.of(2024, 12, 1, 0, 0)

        // Act and Assert
        mockMvc.perform(
            MockMvcRequestBuilders.get("/showtimes/{movieId}", movieEntity.movieId)
                .param("fromTime", fromTime.toString())
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].startTime").value("2024-12-01 20:00:00"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].ticketPrice").value("15.0"))
    }

    @Test
    fun `getShowtimesForMovie should return 404 when no showtimes are found`() {
        // Arrange
        val movieEntity = MovieEntity(
            movieId = 2L,
            title = "The Fast and the Furious 2",
            imdbId = "tt0232501"
        )
        movieRepository.save(movieEntity) // Save the movie with no showtimes

        val fromTime = LocalDateTime.of(2024, 12, 1, 0, 0)

        // Act and Assert
        mockMvc.perform(
            MockMvcRequestBuilders.get("/showtimes/{movieId}", movieEntity.movieId)
                .param("fromTime", fromTime.toString())
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(
                MockMvcResultMatchers.content()
                    .string("Movie you want to find showtimes for does not exist or has no showtimes")
            )
    }

}
