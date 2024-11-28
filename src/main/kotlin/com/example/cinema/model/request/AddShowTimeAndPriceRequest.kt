package com.example.cinema.model.request

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

    @Schema(example = "2022-10-30T12:45:00", type = "string", description = "Date time")
    val newTime: LocalDateTime
)
