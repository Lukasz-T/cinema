package com.example.cinema.model.entity

import jakarta.persistence.*
import lombok.Data
import java.time.LocalDateTime

@Entity
@Data
@Table(name = "showtimes")
class Showtime(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val showtimeId: Long? = null,
    @ManyToOne
    @JoinColumn(name = "movie_id")
    val movie: Movie,
    val showtime: LocalDateTime,
    val price: Double
)