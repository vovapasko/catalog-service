package com.polarbookshop.catalogservice.domain


import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional


interface BookRepository : CrudRepository<Book, Long> {
    fun findByIsbn(isbn: String): Book?
    fun existsByIsbn(isbn: String): Boolean

    @Transactional
    fun deleteByIsbn(isbn: String)
}
