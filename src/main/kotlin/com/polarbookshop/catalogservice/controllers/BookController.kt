package com.polarbookshop.catalogservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController {

    @GetMapping
    fun getBook(): String {
        return "Welcome to the book catalog!"
    }

}