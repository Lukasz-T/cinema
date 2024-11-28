package com.example.cinema.model.request

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime

data class AddShowTimeAndPriceRequest(
    @Schema(
        description = "Id of the movie",
        type = "integer",
        required = true,
        example = "1"
    )
    @get:NotNull
    val movieId: Long,

    val newTicketPrice: BigDecimal,

    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val newTime: LocalDateTime
)
