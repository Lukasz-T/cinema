package com.example.cinema.configuration

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class BeanConfig {

    @Bean
    fun modelMapperBean(): ModelMapper {
        return ModelMapper()
    }
}