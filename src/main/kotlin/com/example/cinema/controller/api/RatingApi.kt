package com.example.cinema.controller.api

import com.example.cinema.model.request.RateMovieRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun submitReview(@RequestBody rateMovieRequest: RateMovieRequest): ResponseEntity<Any>
}