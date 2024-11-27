package com.example.cinema.model.request

import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Range

class RateMovieRequest(
    @get:NotNull
    var movieId: Long,
    @get:NotNull
    @get:Range(min = 0L, max = 5L, message = "Movie rating can only be between 0 and 5")
    var rating: Int
)
