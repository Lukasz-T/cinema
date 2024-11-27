package com.example.cinema.infrastructure.service

import com.example.cinema.infrastructure.repository.MovieRepository
import com.example.cinema.model.entity.MovieEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MovieService(@Autowired val movieRepository: MovieRepository) {
    fun getMovieDetails(id: Long): MovieEntity? {
        return movieRepository.findById(id).orElse(null)
    }

    fun saveMovie(movieEntity: MovieEntity): MovieEntity {
        return movieRepository.save(movieEntity)
    }

    fun getMovieList(): List<MovieEntity> {
        return movieRepository.findAll()
    }
}