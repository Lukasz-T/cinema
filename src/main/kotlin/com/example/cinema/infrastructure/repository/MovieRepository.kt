package com.example.cinema.infrastructure.repository

import com.example.cinema.model.entity.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<MovieEntity, Long> {
}