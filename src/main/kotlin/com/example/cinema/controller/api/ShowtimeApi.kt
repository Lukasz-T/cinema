package com.example.cinema.controller.api

import com.example.cinema.model.entity.Showtime
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime

@RequestMapping("/showtimes")

interface ShowtimeApi {
    @GetMapping("/{movieId}")
    @Operation(summary = "Get list of all movie times")
    fun getShowtimesForMovie(@PathVariable movieId: Long, @RequestParam fromTime: LocalDateTime): List<Showtime>
}