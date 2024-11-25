package com.example.cinema.model.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "reviews")
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reviewId: Long? = null,
    @ManyToOne
    @JoinColumn(name = "movie_id")
    val movie: Movie,
    val rating: Int
)