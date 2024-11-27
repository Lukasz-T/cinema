package com.example.cinema.model.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "ratings")
class RatingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val ratingId: Long? = null,
    @ManyToOne
    @JoinColumn(name = "movie_id")
    var movie: MovieEntity,
    var rating: Int
)