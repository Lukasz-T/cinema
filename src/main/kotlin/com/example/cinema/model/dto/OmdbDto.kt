package com.example.cinema.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OmdbDto(
    @JsonProperty("Title")
    val title: String? = null,

    @JsonProperty("Year")
    val year: String? = null,

    @JsonProperty("Poster")
    val poster: String? = null,

    @JsonProperty("imdbID")
    val imdbID: String? = null,

    @JsonProperty("Type")
    val type: String? = null,

    @JsonProperty("Rated")
    val rated: String? = null,

    @JsonProperty("Released")
    val released: String? = null,

    @JsonProperty("Runtime")
    val runtime: String? = null,

    @JsonProperty("Genre")
    val genre: String? = null,

    @JsonProperty("Director")
    val director: String? = null,

    @JsonProperty("Writer")
    val writer: String? = null,

    @JsonProperty("Actors")
    val actors: String? = null,

    @JsonProperty("Plot")
    val plot: String? = null,

    @JsonProperty("Language")
    val language: String? = null,

    @JsonProperty("Country")
    val country: String? = null,

    @JsonProperty("Awards")
    val awards: String? = null,

    @JsonProperty("Metascore")
    val metascore: String? = null,

    @JsonProperty("imdbRating")
    val imdbRating: String? = null,

    @JsonProperty("imdbVotes")
    val imdbVotes: String? = null,

    @JsonProperty("Response")
    val response: String? = null,

    @JsonProperty("DVD")
    val dvd: String? = null,

    @JsonProperty("BoxOffice")
    val boxOffice: String? = null,

    @JsonProperty("Production")
    val production: String? = null,

    @JsonProperty("Website")
    val webSite: String? = null,

    @JsonProperty("Ratings")
    val ratings: MutableList<OmdbRatingsDto> = mutableListOf()
)