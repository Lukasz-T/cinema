package com.example.cinema.configuration.rest

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleApiException(ex: Exception, request: WebRequest): String {
        logger.error("handleApiException", ex)
        return ""
    }

    @ExceptionHandler(ServiceException::class)
    fun handleServiceException(ex: ServiceException, request: WebRequest): ResponseEntity<Any> {
        logger.error("handleServiceException", ex)
        return ResponseEntity.status(ex.status).body(ex.message)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        logger.error("Handle MethodArgumentNotValidException", ex)
        return ResponseEntity.status(BAD_REQUEST).build()
    }
}
