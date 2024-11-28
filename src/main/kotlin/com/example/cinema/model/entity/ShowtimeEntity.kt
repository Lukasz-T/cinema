package com.example.cinema.model.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
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
    val showtimeId: Long? = null,
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    val movie: MovieEntity,
    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val showtime: LocalDateTime,
    val price: BigDecimal
)