package com.example.cinema.infrastructure.service

import com.example.cinema.configuration.rest.throwServiceException
import com.example.cinema.model.dto.OmdbDto
import com.example.cinema.util.SerializationUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class OmdbService(
    @Value("\${omdb.address}") private val uri: String,
    @Value("\${omdb.api.key}") private val apiKey: String
) {
    val restTemplate: RestTemplate = RestTemplateBuilder().build()
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun fetchMovieDetails(movieId: String): OmdbDto? {
        try {
            val urlTemplate: String = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("apikey", apiKey)
                .queryParam("i", movieId).build().toUriString()
            val response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                HttpEntity(movieId, HttpHeaders().apply { contentType = APPLICATION_JSON }),
                String::class.java
            )
            return SerializationUtils.deserializeObject(response.body, OmdbDto::class.java)
        } catch (ex: HttpClientErrorException) {
            throw throwServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "error during deserialization")
        }
    }
}