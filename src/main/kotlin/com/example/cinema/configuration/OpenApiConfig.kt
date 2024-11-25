package com.example.cinema.configuration

import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfig {

//    @Bean
//    fun customOpenApi(): GroupedOpenApi {
//        return GroupedOpenApi.builder()
//            .group("cinema-api")
//            .pathsToMatch("/**")
//            .addOpenApiCustomizer {openApi ->
//                run {
//                    openApi.info.title("Cinema API")
//                    openApi.info.description("API for managing movies, showtimes, and reviews for a cinema")
//                    openApi.info.version("1.0")
//                }
//            }
//            .build()
//    }
}