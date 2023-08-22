package com.polarbookshop.catalogservice.domain


interface BookRepository {
    fun existsByIsbn(isbn: String): Boolean
    fun findByIsbn(isbn: String): Book?
    fun findAll(): List<Book>
    fun save(book: Book): Book
    fun deleteByIsbn(isbn: String): Boolean
    fun editBook(isbn: String, book: Book): Book?
}
