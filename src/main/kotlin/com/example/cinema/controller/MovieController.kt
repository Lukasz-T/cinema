package com.example.cinema.controller

import com.example.cinema.controller.api.MovieApi
import com.example.cinema.infrastructure.service.MovieService
import com.example.cinema.model.dto.MovieDto
import com.example.cinema.model.dto.OmdbDto
import com.example.cinema.util.ObjectMapperUtils
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(var movieService: MovieService, var modelMapper: ModelMapper):MovieApi {
    override fun getAllMovies(): ResponseEntity<List<MovieDto>> {
        return ResponseEntity.ok(ObjectMapperUtils.mapAll(movieService.getMovieList(), MovieDto::class.java))
    }

    override fun getMovieDetails(@PathVariable movieId: Long): ResponseEntity<OmdbDto> {
        return ResponseEntity.ok(modelMapper.map(movieService.getMovieDetails(movieId), OmdbDto::class.java))
    }
}