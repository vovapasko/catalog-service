package com.polarbookshop.catalogservice.web

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookAlreadyExists
import com.polarbookshop.catalogservice.domain.BookNotFoundException
import com.polarbookshop.catalogservice.domain.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/books")
class BookController(
    val bookService: BookService
) {
    @GetMapping
    fun getAllBooks() = bookService.getAll()

    @GetMapping("/{isbn}")
    fun getBook(@PathVariable isbn: String): ResponseEntity<Book> {
        return bookService.getBookByIsbn(isbn)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun addBook(@RequestBody book: Book): ResponseEntity<Book> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBookToCatalog(book))
        } catch (exception: BookAlreadyExists) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @DeleteMapping("/{isbn}")
    fun deleteBook(@PathVariable isbn: String): ResponseEntity<String> {
        try {
            bookService.removeBookFromCatalog(isbn)
        } catch (exception: BookNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book $isbn was deleted successfully")
    }

    @PutMapping("/{isbn}")
    fun editBook(@PathVariable isbn: String, @RequestBody book: Book): ResponseEntity<Book> {
        try {
            val editedBook = bookService.editBook(isbn, book)
            return ResponseEntity.status(HttpStatus.OK).body(editedBook)
        } catch (exception: BookNotFoundException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

}