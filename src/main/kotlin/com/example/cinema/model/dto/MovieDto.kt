package com.example.cinema.model.dto

import com.example.cinema.model.dto.RatingDto
import com.example.cinema.model.dto.ShowingDto

data class MovieDto(
    var movieId: Long,
    var title: String,
    var imdbId: String,
    var showings: List<ShowingDto>? = null,
    var ratings: List<RatingDto>? = null
)
