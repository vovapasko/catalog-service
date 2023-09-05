package com.polarbookshop.catalogservice

import com.polarbookshop.catalogservice.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `create book returns created book`() {
        val expectedBook = Book.of("1231231231", "Title", "Author", 9.90)

        webTestClient
            .post()
            .uri("/books")
            .bodyValue(expectedBook)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Book::class.java).value { actualBook ->
                run {
                    assertThat(actualBook.isbn).isEqualTo(expectedBook.isbn)
                    assertThat(actualBook.author).isEqualTo(expectedBook.author)
                    assertThat(actualBook.title).isEqualTo(expectedBook.title)
                    assertThat(actualBook.price).isEqualTo(expectedBook.price)
                }
            }
    }


}
