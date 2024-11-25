package com.example.cinema.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OmdbRatingsDto(
    @JsonProperty("Source")
    val source: String?,
    @JsonProperty("Value")
    val value: String?
)
