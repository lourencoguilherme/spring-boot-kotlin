package com.guimtlo.digitalinovationone.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorld {
    @GetMapping
    fun helloWorld() : String {
        return "teste kotlin"
    }
}