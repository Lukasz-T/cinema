package com.example.cinema.configuration

import com.example.cinema.infrastructure.repository.MovieRepository
import com.example.cinema.model.entity.Movie
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Configuration

@Configuration
class H2DataInitializer(private val repository: MovieRepository) :InitializingBean{
    override fun afterPropertiesSet() {
        repository.save(Movie(null,"The Fast and the Furious",null,null,null,null,null,"tt0232500"))
        repository.save(Movie(null,"2 Fast 2 Furious",null,null,null,null,null,"tt0322259"))
        repository.save(Movie(null,"The Fast and the Furious: Tokyo Drift",null,null,null,null,null,"tt0463985"))
        repository.save(Movie(null,"Fast & Furious",null,null,null,null,null,"tt1013752"))
        repository.save(Movie(null,"Fast Five",null,null,null,null,null,"tt1596343"))
        repository.save(Movie(null,"Fast & Furious 6",null,null,null,null,null,"tt1905041"))
        repository.save(Movie(null,"Furious 7",null,null,null,null,null,"tt2820852"))
        repository.save(Movie(null,"The Fate of the Furious",null,null,null,null,null,"tt4630562"))
        repository.save(Movie(null,"F9: The Fast Saga",null,null,null,null,null,"tt5433138"))
    }
}