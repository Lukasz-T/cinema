package com.example.cinema.infrastructure.service

import com.example.cinema.infrastructure.repository.MovieRepository
import com.example.cinema.model.entity.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MovieService(@Autowired val movieRepository: MovieRepository) {
    fun getMovieDetails(id: Long): Movie? {
        return movieRepository.findById(id).orElse(null)
    }

    fun saveMovie(movie: Movie): Movie {
        return movieRepository.save(movie)
    }

    fun getMovieList(): List<Movie> {
        return movieRepository.findAll()
    }
}