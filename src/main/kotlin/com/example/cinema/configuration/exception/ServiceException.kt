package com.example.cinema.configuration.exception

import org.springframework.http.HttpStatus

data class ServiceException(val status: HttpStatus, override val message: String) : RuntimeException(message)

fun throwServiceException(status: HttpStatus, message: String): Nothing = throw ServiceException(status, message)
