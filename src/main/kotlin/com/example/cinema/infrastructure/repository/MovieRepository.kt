package com.example.cinema.infrastructure.repository

import com.example.cinema.model.entity.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<Movie, Long> {
    fun findByTitle(title: String): Movie?
}