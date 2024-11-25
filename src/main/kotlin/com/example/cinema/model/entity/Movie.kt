package com.example.cinema.model.entity

import jakarta.persistence.*
import lombok.Data
import lombok.RequiredArgsConstructor

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "movies")
class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val movieId: Long? = null,
    val title: String?,
    val description: String?,
    val releaseDate: String?,
    val rating: Double?,
    val imdbRating: Double?,
    val runtime: String?,
    val imdbId: String?
)
