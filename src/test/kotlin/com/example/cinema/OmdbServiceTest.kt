package com.example.cinema

import com.example.cinema.infrastructure.service.OmdbService
import com.example.cinema.model.dto.OmdbDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import kotlin.test.Test

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class OmdbServiceTest {

    private var restTemplate: RestTemplate = RestTemplate()

    @Mock
    private lateinit var omdbService: OmdbService

    @Value("\${omdb.address}")
    private lateinit var uri: String

    @Value("\${omdb.api.key}")
    private lateinit var apiKey: String

    private val movieId = "tt0232500"
    private val omdbDto = OmdbDto("Inception", "2010", "8.8", "9.0")

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val restTemplateField = OmdbService::class.java.getDeclaredField("restTemplate")
        restTemplateField.isAccessible = true
        restTemplateField.set(omdbService, restTemplate)
    }

    @Test
    fun `fetchMovieDetails should return OmdbDto when response is valid`() {
        val urlTemplate = "$uri?apikey=$apiKey&i=$movieId"
        val responseEntity = ResponseEntity(
            "{\"title\":\"Inception\",\"year\":\"2010\",\"imdbRating\":\"8.8\",\"rating\":\"9.0\"}",
            HttpStatus.OK
        )

        `when`(
            restTemplate.exchange(
                eq(urlTemplate),
                eq(HttpMethod.GET),
                any(HttpEntity::class.java),
                eq(String::class.java)
            )
        ).thenReturn(responseEntity)

        val result = omdbService.fetchMovieDetails(movieId)

        assertNotNull(result)
        assertEquals(omdbDto.title, result?.title)
        assertEquals(omdbDto.year, result?.year)
        assertEquals(omdbDto.imdbRating, result?.imdbRating)
    }

    @Test
    fun `fetchMovieDetails should return null when response body is null`() {
        val urlTemplate = "$uri?apikey=$apiKey&i=$movieId"
        val responseEntity = ResponseEntity("null", HttpStatus.OK)

        `when`(
            restTemplate.exchange(
                eq(urlTemplate),
                eq(HttpMethod.GET),
                any(HttpEntity::class.java),
                eq(String::class.java)
            )
        ).thenReturn(responseEntity)

        val result = omdbService.fetchMovieDetails(movieId)

        assertNull(result)
    }

    @Test
    fun `fetchMovieDetails should throw exception when API returns error`() {
        val urlTemplate = "$uri?apikey=$apiKey&i=$movieId"

        // Mock RestTemplate exchange method to throw an exception
        `when`(
            restTemplate.exchange(
                eq(urlTemplate),
                eq(HttpMethod.GET),
                any(HttpEntity::class.java),
                eq(String::class.java)
            )
        ).thenThrow(HttpClientErrorException(HttpStatus.BAD_REQUEST))

        val exception = assertThrows<HttpClientErrorException> {
            omdbService.fetchMovieDetails(movieId)
        }

        assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
    }
}
