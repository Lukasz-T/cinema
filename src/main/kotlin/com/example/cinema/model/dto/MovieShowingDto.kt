package com.example.cinema.model.dto

data class MovieShowingDto(
    val title: String? = null,
    val showings: List<ShowtimeDto>? = null
)
