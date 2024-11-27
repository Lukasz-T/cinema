package com.example.cinema.model.dto

data class MovieShortDto(
    val title: String? = null,
    val showings: List<ShowtimeDto>? = null
)
