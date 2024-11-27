package com.example.cinema.model.entity


import com.fasterxml.jackson.annotation.JsonManagedReference
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
    val movieId: Long?,
    var title: String? = null,
    var imdbId: String? = null,
    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "movie")
    var showtimes: List<ShowtimeEntity>? = null,
    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "movie")
    var ratings: List<RatingEntity>? = null
)
