package com.polarbookshop.catalogservice.web

import com.polarbookshop.catalogservice.domain.Book
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester


@JsonTest
internal class BookJsonTests {
    @Autowired
    private val json: JacksonTester<Book>? = null

    @Test
    @Throws(Exception::class)
    fun testSerialize() {
        val book = Book.of("1234567890", "Title", "Author", 9.90, "My publisher")
        val jsonContent = json!!.write(book)
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
            .isEqualTo(book.isbn)
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.title")
            .isEqualTo(book.title)
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.author")
            .isEqualTo(book.author)
        Assertions.assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
            .isEqualTo(book.price)
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.publisher")
            .isEqualTo(book.publisher)
    }

    @Test
    @Throws(Exception::class)
    fun testDeserialize() {
        val content = """
                {
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 9.90,
                    "publisher": "My super publisher"
                }
                
                """.trimIndent()
        Assertions.assertThat(
            json!!.parse(content)
        )
            .usingRecursiveComparison()
            .isEqualTo(Book.of("1234567890", "Title", "Author", 9.90, "My super publisher"))
    }
}

