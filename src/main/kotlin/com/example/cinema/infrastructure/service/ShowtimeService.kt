package com.example.cinema.infrastructure.service

import com.example.cinema.infrastructure.repository.ShowtimeRepository
import com.example.cinema.model.entity.Showtime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ShowtimeService(@Autowired val showtimeRepository: ShowtimeRepository) {
    fun getShowtimesForMovie(movieId: Long, fromTime: LocalDateTime): List<Showtime> {
        return showtimeRepository.findByMovieMovieIdAndShowtimeAfter(movieId, fromTime)
    }

    fun saveShowtime(showtime: Showtime): Showtime {
        return showtimeRepository.save(showtime)
    }
}