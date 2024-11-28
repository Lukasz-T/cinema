package com.example.cinema.unit

import com.example.cinema.configuration.exception.ServiceException
import com.example.cinema.infrastructure.service.OmdbService
import com.example.cinema.model.dto.OmdbDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import kotlin.test.Test

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class OmdbServiceTest {
    @Autowired
    private lateinit var omdbService: OmdbService

    @Mock
    private lateinit var restTemplate: RestTemplate

    @Value("\${omdb.address}")
    private lateinit var uri: String

    @Value("\${omdb.api.key}")
    private lateinit var apiKey: String

    private val movieId = "tt0232500"
    private val invalidMovieId = "abc"
    private val omdbDto = OmdbDto("The Fast and the Furious", "2001", imdbRating = "6.8")

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val restTemplateField = OmdbService::class.java.getDeclaredField("restTemplate")
        restTemplateField.isAccessible = true
        restTemplateField.set(omdbService, restTemplate)
    }

    @Test
    fun `fetchMovieDetails returns OmdbDto when response is valid`() {
        val urlTemplate = "$uri?apikey=$apiKey&i=$movieId"
        val responseEntity = ResponseEntity(
            "{\"Title\":\"The Fast and the Furious\",\"Year\":\"2001\",\"Rated\":\"PG-13\",\"Released\":\"22 Jun 2001\",\"Runtime\":\"106 min\",\"Genre\":\"Action, Crime, Thriller\",\"Director\":\"Rob Cohen\",\"Writer\":\"Ken Li, Gary Scott Thompson, Erik Bergquist\",\"Actors\":\"Vin Diesel, Paul Walker, Michelle Rodriguez\",\"Plot\":\"Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to end it.\",\"Language\":\"English, Spanish\",\"Country\":\"United States, Germany\",\"Awards\":\"11 wins & 18 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZGRiMDE1NTMtMThmZS00YjE4LWI1ODQtNjRkZGZlOTg2MGE1XkEyXkFqcGc@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"6.8/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"55%\"},{\"Source\":\"Metacritic\",\"Value\":\"58/100\"}],\"Metascore\":\"58\",\"imdbRating\":\"6.8\",\"imdbVotes\":\"427,944\",\"imdbID\":\"tt0232500\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"\$144,745,925\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}",
            HttpStatus.OK
        )

        lenient().`when`(
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
    fun `fetchMovieDetails returns error when response body is null`() {
        val urlTemplate = "$uri?apikey=$apiKey&i=$invalidMovieId"
        val responseEntity = ResponseEntity("", HttpStatus.OK)

        lenient().`when`(
            restTemplate.exchange(
                eq(urlTemplate),
                eq(HttpMethod.GET),
                any(HttpEntity::class.java),
                eq(String::class.java)
            )
        ).thenReturn(responseEntity)
        assertThrows(ServiceException::class.java) { omdbService.fetchMovieDetails(invalidMovieId) }
    }
}
