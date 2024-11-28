package com.example.cinema.controller.api

import com.example.cinema.model.dto.MovieShortDto
import com.example.cinema.model.dto.OmdbDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200"
            )
        ]
    )
    fun getAllMovies(): ResponseEntity<List<MovieShortDto>>

    @GetMapping("/{movieId}")
    @Operation(summary = "Get details of a movie")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200"
            ), ApiResponse(
                responseCode = "404", description = "Cannot find movie details for the given ID",
            )
        ]
    )
    fun getMovieDetails(
        @PathVariable movieId: Long
    ): ResponseEntity<OmdbDto>


}
