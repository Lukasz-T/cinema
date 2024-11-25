package com.example.cinema.controller

import com.example.cinema.controller.api.ShowtimeApi
import com.example.cinema.infrastructure.service.ShowtimeService
import com.example.cinema.model.entity.Showtime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
class ShowtimeController(@Autowired val showtimeService: ShowtimeService): ShowtimeApi {

    override fun getShowtimesForMovie(@PathVariable movieId: Long, @RequestParam fromTime: LocalDateTime): List<Showtime> {
        return showtimeService.getShowtimesForMovie(movieId, fromTime)
    }
}