package com.example.cinema.controller

import com.example.cinema.controller.api.ReviewApi
import com.example.cinema.infrastructure.service.ReviewService
import com.example.cinema.model.entity.Review
import com.example.cinema.model.request.RateMovie
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class ReviewController(var reviewService: ReviewService, var modelMapper: ModelMapper): ReviewApi {

    override fun submitReview(@PathVariable movieId: Long, @RequestBody rateMovie: RateMovie) {
        val review:Review = modelMapper.map(rateMovie,Review::class.java)
        reviewService.saveReview(review)
    }
}