package com.polarbookshop.catalogservice.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.time.Instant


data class Book(
    @Id
    val id: Long?,

    @get:NotBlank(message = "The book ISBN must be defined.")
    @get:Pattern(
        regexp = "^([0-9]{10}|[0-9]{13})\$",
        message = "The ISBN format must be valid."
    )
    val isbn: String,
    @get:NotBlank(message = "The book title must be defined.")
    val title: String,
    @get:NotBlank(message = "The book title must be defined.")
    val author: String,
    @get:NotNull(message = "The book price must be defined.")
    @get:Positive(message = "The book price must be greater than zero.")
    val price: Double,

    @CreatedDate
    val createdDate: Instant?,

    @LastModifiedDate
    val lastModifiedDate: Instant?,


    @Version
    val version: Int

) {
    companion object {
        fun of(isbn: String, title: String, author: String, price: Double) = Book(
            null, isbn, title, author, price, null, null, 0
        )
    }
}
