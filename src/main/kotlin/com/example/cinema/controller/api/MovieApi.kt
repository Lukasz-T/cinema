package com.example.cinema.controller.api

import com.example.cinema.model.dto.MovieShortDto
import com.example.cinema.model.dto.OmdbDto
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController

@RequestMapping("/movies")
interface MovieApi {

    @GetMapping("/all")
    @Operation(summary = "Get list of all movies")
    fun getAllMovies(): ResponseEntity<List<MovieShortDto>>

    @GetMapping("/{movieId}")
    @Operation(summary = "Get details of a movie")
    fun getMovieDetails(
        @PathVariable movieId: Long
    ): ResponseEntity<OmdbDto>


}
