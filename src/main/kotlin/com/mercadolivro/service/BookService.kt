package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class BookService(val bookRepository: BookRepository) {
    fun create(book: BookModel) {
        bookRepository.save(book)
    }


    fun findAll(pageable: Pageable): Page<BookModel> = bookRepository.findAll(pageable)


    fun findActive(pageable: Pageable): Page<BookModel> = bookRepository.findByStatus(BookStatus.ATIVO, pageable)

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow{NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code)}
    }

    fun delete(id: Int){
        val book = findById(id)
        book.status = BookStatus.DELETADO

        update(book)
    }

    fun update(book: BookModel){
       bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books =  bookRepository.findByCustomer(customer)
        for( book in books){
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }


}
