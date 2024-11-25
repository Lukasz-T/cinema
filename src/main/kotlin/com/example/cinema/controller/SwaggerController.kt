package com.example.cinema.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class SwaggerController {

    @RequestMapping(value = ["/"])
    fun index(): String = "redirect:swagger-ui/index.html"
}