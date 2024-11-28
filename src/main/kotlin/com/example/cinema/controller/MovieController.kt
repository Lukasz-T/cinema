package com.example.cinema.controller

import com.example.cinema.controller.api.MovieApi
import com.example.cinema.infrastructure.service.MovieService
import com.example.cinema.model.dto.MovieShortDto
import com.example.cinema.model.dto.OmdbDto
import com.example.cinema.util.ObjectMapperUtils
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(private val movieService: MovieService) : MovieApi {
    override fun getAllMovies(): ResponseEntity<List<MovieShortDto>> {

        return ResponseEntity.ok(movieService.getMovieList().stream().map { entity ->
            ObjectMapperUtils.toShortDto(entity)
        }
            .toList()
        )
    }

    override fun getMovieDetails(@PathVariable movieId: Long): ResponseEntity<OmdbDto> {
        return ResponseEntity.ok((movieService.getMovieDetails(movieId)))
    }
}