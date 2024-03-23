package com.mercadolivro.extesion

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.enums.CustomerStatus

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previuesValue: CustomerModel): CustomerModel {
    return CustomerModel(
        id = previuesValue.id,
        name = this.name,
        email = this.email,
        status = previuesValue.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(name = this.name, price = this.price, status = BookStatus.ATIVO, customer = customer)
}

fun PutBookRequest.toBookModel(previusValue: BookModel): BookModel {
    return BookModel(
        id = previusValue.id,
        name = this.name ?: previusValue.name,
        price = this.price ?: previusValue.price,
        status = previusValue.status,
        customer = previusValue.customer
    )
}