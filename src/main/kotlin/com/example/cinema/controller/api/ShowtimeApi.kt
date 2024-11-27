package com.example.cinema.controller.api

import com.example.cinema.model.entity.ShowtimeEntity
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController

@RequestMapping("/showtimes")

interface ShowtimeApi {
    @GetMapping("/{movieId}")
    @Operation(summary = "Get list of all movie times")
    fun getShowtimesForMovie(@PathVariable movieId: Long, @RequestParam fromTime: LocalDateTime): List<ShowtimeEntity>
}