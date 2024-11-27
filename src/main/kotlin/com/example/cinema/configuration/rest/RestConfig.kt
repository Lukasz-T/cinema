package com.example.demo.config.rest

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration.ofMillis

@Configuration
class RestConfig {

    @Bean
    fun defaultRestTemplate(@Value("\${http.timeout.default}") timeout: Long): RestTemplate =
        createRestTemplate(timeout)

    private fun createRestTemplate(timeout: Long): RestTemplate = RestTemplateBuilder()
        .setConnectTimeout(ofMillis(timeout))
        .setReadTimeout(ofMillis(timeout))
        .build()
}
