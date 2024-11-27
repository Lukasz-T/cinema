package com.example.cinema.model.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class ShowtimeDto(
    var startTime: LocalDateTime,
    var ticketPrice: BigDecimal
)
