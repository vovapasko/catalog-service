package com.polarbookshop.catalogservice.web

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookService
import jakarta.validation.Valid
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
    fun addBook(@Valid @RequestBody book: Book): ResponseEntity<Book> {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBookToCatalog(book))
    }

    @DeleteMapping("/{isbn}")
    fun deleteBook(@PathVariable isbn: String): ResponseEntity<String> {
        bookService.removeBookFromCatalog(isbn)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book $isbn was deleted successfully")
    }

    @PutMapping("/{isbn}")
    fun editBook(@PathVariable isbn: String, @Valid @RequestBody book: Book): ResponseEntity<Book> {
        val editedBook = bookService.editBook(isbn, book)
        return ResponseEntity.status(HttpStatus.OK).body(editedBook)
    }

}