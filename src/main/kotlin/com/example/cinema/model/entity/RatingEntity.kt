package com.example.cinema.model.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "ratings")
class RatingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val ratingId: Long?,
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    var movie: MovieEntity,
    var rating: Int
)