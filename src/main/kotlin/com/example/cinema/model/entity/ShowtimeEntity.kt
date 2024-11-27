package com.example.cinema.model.entity

import jakarta.persistence.*
import lombok.Data
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Data
@Table(name = "showtimes")
class ShowtimeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val showtimeId: Long,
    @ManyToOne
    @JoinColumn(name = "movie_id")
    val movie: MovieEntity,
    val showtime: LocalDateTime,
    val price: BigDecimal
)