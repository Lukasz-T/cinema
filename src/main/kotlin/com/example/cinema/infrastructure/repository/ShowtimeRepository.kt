package com.example.cinema.infrastructure.repository

import com.example.cinema.model.entity.Showtime
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ShowtimeRepository : JpaRepository<Showtime, Long> {
    fun findByMovieMovieIdAndShowtimeAfter(movieId: Long, showtime: LocalDateTime): List<Showtime>
}