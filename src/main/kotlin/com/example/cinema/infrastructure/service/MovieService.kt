package com.example.cinema.infrastructure.service

import com.example.cinema.configuration.exception.throwServiceException
import com.example.cinema.infrastructure.repository.MovieRepository
import com.example.cinema.model.dto.OmdbDto
import com.example.cinema.model.entity.MovieEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService(val movieRepository: MovieRepository, val omdbService: OmdbService) {
    fun getMovieDetails(id: Long): OmdbDto =
        movieRepository.findByIdOrNull(id)
            ?.imdbId
            ?.let { omdbService.fetchMovieDetails(it) }
            ?: throwServiceException(HttpStatus.NOT_FOUND, "Cannot find movie details for the given ID")

    fun saveMovie(movieEntity: MovieEntity): MovieEntity {
        return movieRepository.save(movieEntity)
    }

    fun getMovie(id: Long): Optional<MovieEntity> {
        return movieRepository.findById(id)
    }

    fun getMovieList(): List<MovieEntity> {
        return movieRepository.findAll()
    }
}