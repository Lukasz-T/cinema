package com.example.cinema.model.dto

import java.math.BigDecimal

data class MovieRatingDto(
    val title: String? = null,
    val rating: BigDecimal? = null
)