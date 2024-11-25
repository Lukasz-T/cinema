package com.example.cinema.controller.api

import com.example.cinema.model.dto.MovieDto
import com.example.cinema.model.dto.OmdbDto
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/movies")
interface MovieApi {

    @GetMapping("movie/all")
    @Operation(summary = "Get list of all movies")
    fun getAllMovies(): ResponseEntity<List<MovieDto>>

    @GetMapping("/{movieId}")
    @Operation(summary = "Get details of a movie")
    fun getMovieDetails(
        @PathVariable movieId: Long
    ): ResponseEntity<OmdbDto>



}
