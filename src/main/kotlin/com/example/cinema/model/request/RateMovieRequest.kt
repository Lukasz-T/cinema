package com.example.cinema.model.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class RateMovieRequest(
    @get:NotNull
    val movieId: Long,
    @get:NotNull
    @get:Size(min = 0, max = 5, message = "Movie rating can only be between 0 and 5")
    val rating: Int
)
