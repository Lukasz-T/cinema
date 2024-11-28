package com.example.cinema.controller.api

import com.example.cinema.model.dto.MovieRatingDto
import com.example.cinema.model.request.RateMovieRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ratings")
interface RatingApi {

    @PostMapping("/addRating")
    @Operation(summary = "Rate movie")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Movie successfully rated"
            ), ApiResponse(
                responseCode = "404", description = "Movie you want to rate does not exist",
            )
        ]
    )
    fun submitRating(@RequestBody rateMovieRequest: RateMovieRequest): ResponseEntity<Any>

    @GetMapping("/{movieId}")
    @Operation(summary = "Get average movie rating")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200"
            ), ApiResponse(
                responseCode = "404", description = "Movie doesn't exist",
            )
        ]
    )
    fun getRatingForMoview(
        @PathVariable movieId: Long,
    ): ResponseEntity<MovieRatingDto>


}