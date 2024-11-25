package com.example.cinema.model.dto

import com.example.cinema.model.dto.ShowingDto

data class MovieShortDto(
    var title: String,
    var showings: List<ShowingDto>? = null
)
