package com.example.cinema.model.dto

data class MovieDto(
    var movieId: Long,
    var title: String,
    var imdbId: String,
    var showings: List<ShowtimeDto>? = null,
    var ratings: List<RatingDto>? = null
)
