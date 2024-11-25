package com.example.cinema.util

import org.modelmapper.ModelMapper
import java.util.stream.Collectors


class ObjectMapperUtils() {
    companion object {
        private var modelMapper: ModelMapper = ModelMapper()

        fun <D, T> map(entity: T, outClass: Class<D>?): D {
        return modelMapper.map(entity, outClass)
        }
    fun <D, T> mapAll(entityList: Collection<T>, outCLass: Class<D>?): List<D> {
        return entityList.stream()
            .map<Any> { entity: T -> map(entity, outCLass) }
            .collect(Collectors.toList()) as List<D>
    }
}
}