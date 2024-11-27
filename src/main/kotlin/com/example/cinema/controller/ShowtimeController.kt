package com.example.cinema.controller

import com.example.cinema.controller.api.ShowtimeApi
import com.example.cinema.infrastructure.service.ShowtimeService
import com.example.cinema.model.entity.ShowtimeEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class ShowtimeController(@Autowired val showtimeService: ShowtimeService) : ShowtimeApi {

    override fun getShowtimesForMovie(
        @PathVariable movieId: Long,
        @RequestParam fromTime: LocalDateTime
    ): List<ShowtimeEntity> {
        return showtimeService.getShowtimesForMovie(movieId, fromTime)
    }
}