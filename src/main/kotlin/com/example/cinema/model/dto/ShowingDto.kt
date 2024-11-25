package com.example.cinema.model.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class ShowingDto(
    var showingId: Long,
    var startTime: LocalDateTime,
    var ticketPrice: BigDecimal
)
