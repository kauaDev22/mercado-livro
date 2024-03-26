package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.enums.CustomerStatus
import com.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService : BookService
) {

    fun getAll(name: String?, pageable: Pageable): Page<CustomerModel> {
        return if (name != null) {
            customerRepository.findByNameContaining(name, pageable)
        } else {
            val customers = customerRepository.findAll()
            val pageCustomers = PageImpl<CustomerModel>(customers.toList(), pageable, customers.count().toLong())
            pageCustomers
        }
    }



    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)
    }

}