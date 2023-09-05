package com.polarbookshop.catalogservice.persistence

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryBookRepository : BookRepository {
    private val booksCatalog: MutableMap<String, Book> = ConcurrentHashMap()

    override fun existsByIsbn(isbn: String): Boolean {
        return booksCatalog[isbn] != null
    }

    override fun findByIsbn(isbn: String): Book? {
        return booksCatalog[isbn]
    }

    override fun findAll(): List<Book> {
        return booksCatalog.values.toList()
    }

    override fun save(book: Book): Book {
        booksCatalog[book.isbn] = book
        return book
    }

    override fun deleteByIsbn(isbn: String): Boolean {
        return booksCatalog.remove(isbn) != null
    }
}