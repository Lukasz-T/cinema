package com.example.cinema.controller.api

import com.example.cinema.model.request.RateMovie
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/reviews")

interface ReviewApi {

    @PostMapping("/{movieId}")
    @Operation(summary = "Rate movie")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Movie successfully rated"
            ), ApiResponse(
                responseCode = "400", description = "Invalid request data",
            )
        ]
    )
    fun submitReview(@PathVariable movieId: Long, @RequestBody rateMovie: RateMovie)
}