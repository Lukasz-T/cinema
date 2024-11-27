package com.example.cinema.model.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class ShowtimeDto(
    val startTime: LocalDateTime,
    val ticketPrice: BigDecimal
)
