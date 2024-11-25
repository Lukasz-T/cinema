//package com.example.cinema.configuration
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.bind.annotation.RestController
//
//
//@Configuration
//@EnableSwagger3
//class SwaggerConfig {
//
//    fun apiInfo(
//        applicationName: String?
//    ):
//        ApiInfo =
//        ApiInfoBuilder()
//            .title(applicationName)
//            .build()
//
//    @Bean
//    fun docket(
//        @Value("\${application.name}")
//        applicationName: String? = null
//    ): Docket = Docket(DocumentationType.SWAGGER_2)
//        .useDefaultResponseMessages(false)
//        .select()
//        .paths(PathSelectors.any())
//        .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
//        .build()
//        .apiInfo(
//            apiInfo(
//                applicationName
//            )
//        )
//}
