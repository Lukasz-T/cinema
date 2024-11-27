package com.example.cinema.infrastructure.repository

import com.example.cinema.model.entity.ShowtimeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.*

interface ShowtimeRepository : JpaRepository<ShowtimeEntity, Long> {
    fun findByMovieMovieIdAndShowtimeAfter(movieId: Long, showtime: LocalDateTime): Optional<List<ShowtimeEntity>>
}