package com.example.cinema.util

import com.example.cinema.configuration.rest.throwServiceException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import java.io.IOException

object SerializationUtils {
    private val mapper = ObjectMapper()
    private val logger = LoggerFactory.getLogger(SerializationUtils::class.java)

    fun serializeObject(obj: Any?): String? {
        return try {
            mapper.writeValueAsString(obj)
        } catch (e: Exception) {
            logger.error("Cannot serialize object:", e)
            throwServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot serialize object: " + e)
        }
    }

    fun <T : Any?> deserializeObject(json: String?, clazz: Class<T>?): T? {
        return try {
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            mapper.configure(
                DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true
            )
            mapper.readValue(json, clazz)
        } catch (e: IOException) {
            logger.error("Cannot deserialize String:", e)
            throwServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot deserialize String: " + e)

        }
    }
}
