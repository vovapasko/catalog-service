package com.polarbookshop.catalogservice

import com.polarbookshop.catalogservice.domain.Book
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class BookValidationTest {
    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun `test book validation succeeds`() {
        val book = Book(
            "1234567890", "my super book",
            "Jon Doe", 12.33
        )
        val violations = validator.validate(book)
        assert(violations.isEmpty())
    }

    @Test
    fun `test book validation fails`() {
        val book = Book(
            "", "my super book",
            "Jon Doe", 12.33
        )
        val violations = validator.validate(book)
        assert(violations.size == 2)
        assertThat(violations.iterator().next().message == "The book ISBN must be defined.")
    }

    @Test
    fun `test book price validation fails`() {
        val book = Book(
            "1234567890", "my super book",
            "Jon Doe", -12.33
        )
        val violations = validator.validate(book)
        assert(violations.size == 1)
        assertThat(violations.iterator().next().message == "The book price must be greater than zero.")
    }


}