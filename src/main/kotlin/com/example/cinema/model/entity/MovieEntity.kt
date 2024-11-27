package com.example.cinema.model.entity


import jakarta.persistence.*
import lombok.Data
import lombok.RequiredArgsConstructor

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "movies")
class MovieEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var movieId: Long?,
    var title: String? = null,
    var imdbId: String? = null,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "movie")
    var showtimes: List<ShowtimeEntity>? = null,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "movie")
    var ratings: List<RatingEntity>? = null
)
