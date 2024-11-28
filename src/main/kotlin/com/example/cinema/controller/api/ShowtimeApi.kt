package com.example.cinema.controller.api

import com.example.cinema.model.dto.ShowtimeDto
import com.example.cinema.model.request.AddShowTimeAndPriceRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController

@RequestMapping("/showtimes")

interface ShowtimeApi {
    @GetMapping("/{movieId}")
    @Operation(summary = "Get list of all movie times")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Movie successfully rated"
            ), ApiResponse(
                responseCode = "404",
                description = "Movie you want to find showtimes for does not exist or has no showtimes"
            )
        ]
    )
    fun getShowtimesForMovie(
        @PathVariable movieId: Long,
        @RequestParam
        @Schema(example = "2022-10-30T12:45:00", type = "string", description = "Date time")
        fromTime: LocalDateTime
    ): ResponseEntity<List<ShowtimeDto>>

    @PostMapping("/add")
    @Operation(summary = "Add new timings and their prices for a movie from your catalogue")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "New showtime and price saved"
            ), ApiResponse(
                responseCode = "404", description = "Movie you want to add new showtime and price to does not exist",
            )
        ]
    )
    fun saveShowTimeAndPrice(addShowTimeAndPriceRequest: AddShowTimeAndPriceRequest): ResponseEntity<Any>
}