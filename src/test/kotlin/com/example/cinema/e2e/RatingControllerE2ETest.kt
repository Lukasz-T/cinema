package com.example.cinema.e2e

import com.example.cinema.infrastructure.service.MovieService
import com.example.cinema.model.entity.MovieEntity
import com.example.cinema.model.request.RateMovieRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class RatingControllerE2ETest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var movieService: MovieService

    private val objectMapper = ObjectMapper()

    @BeforeEach
    fun setup() {

        val movieEntity = MovieEntity(
            movieId = 1L,
            title = "The Fast and the Furious",
            imdbId = "tt0232500"
        )
        movieService.saveMovie(movieEntity)
    }

    @Test
    fun `submitReview should return 200 when movie exists and rating is submitted`() {
        val rateMovieRequest = RateMovieRequest(
            movieId = 1L,
            rating = 8
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/ratings/addRating")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rateMovieRequest))
        )
            .andExpect(
                status().isOk
            )
    }

    @Test
    fun `submitReview should return 404 when movie does not exist`() {
        val rateMovieRequest = RateMovieRequest(
            movieId = 999L, rating = 7
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/ratings/addRating")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rateMovieRequest))
        )
            .andExpect(status().isNotFound)
    }

    @Test
    fun `should return average rating and title for existing movie`() {
        val movieId = movieService.getMovieList().first().movieId

        mockMvc.perform(MockMvcRequestBuilders.get("/ratings/$movieId"))
            .andExpect(status().isOk)
    }

    @Test
    fun `should return 404 when movie does not exist`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/ratings/9999"))
            .andExpect(status().isNotFound)
    }
}