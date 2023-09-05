package com.polarbookshop.catalogservice.demo

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@Profile("testdata")
class BookDataLoader(val bookRepository: BookRepository) {

    @EventListener(ApplicationReadyEvent::class)
    fun loadBookTestData() {
        val book1 = Book.of(
            "1234567891", "Northern Lights",
            "Lyra Silverstar", 9.90
        )
        val book2 = Book.of(
            "1234567892", "Polar Journey",
            "Iorek Polarson", 12.90
        )
        bookRepository.save(book1)
        bookRepository.save(book2)
    }
}