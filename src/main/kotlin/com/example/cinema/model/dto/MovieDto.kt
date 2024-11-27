package com.example.cinema.model.dto

data class MovieDto(
    val title: String?,
    val imdbId: String?,
    val showings: List<ShowtimeDto>? = null,
    val ratings: List<RatingDto>? = null
)
