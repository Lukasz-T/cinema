package com.example.cinema.infrastructure.service

import com.example.cinema.model.dto.OmdbDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OmdbService {

    @Value("\${omdb.api.key}")
    lateinit var apiKey: String

    fun fetchMovieDetails(title: String): OmdbDto? {
        val url = "http://www.omdbapi.com/?t=$title&apikey=$apiKey"
        val restTemplate = RestTemplate()
        val response = restTemplate.getForObject(url, OmdbDto::class.java)
        return response
    }


}