package com.polarbookshop.catalogservice.domain

import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun getAll() = bookRepository.findAll()

    fun getBookByIsbn(isbn: String): Book? {
        return bookRepository.findByIsbn(isbn) ?: throw BookNotFoundException(isbn)
    }

    fun addBookToCatalog(book: Book): Book {
        if (bookRepository.existsByIsbn(book.isbn)) {
            throw BookAlreadyExists(book)
        }
        return bookRepository.save(book)
    }

    fun removeBookFromCatalog(isbn: String) = bookRepository.deleteByIsbn(isbn)

    fun editBook(isbn: String, book: Book): Book {
        return bookRepository.findByIsbn(isbn)?.let {
            val updatedBook = Book(
                it.id,
                book.isbn,
                book.title,
                book.author,
                book.price,
                it.publisher,
                it.createdDate,
                it.lastModifiedDate,
                it.version
            )
            bookRepository.save(updatedBook)
        } ?: throw BookNotFoundException(isbn)
    }


}

class BookAlreadyExists(book: Book) : RuntimeException("Book with isbn ${book.isbn} already exists")

class BookNotFoundException(isbn: String) : RuntimeException("The book with ISBN $isbn was not found")