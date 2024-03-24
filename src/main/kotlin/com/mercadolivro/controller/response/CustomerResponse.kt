package com.mercadolivro.controller.response

import com.mercadolivro.model.enums.CustomerStatus
import jakarta.persistence.*

data class CustomerResponse (
    var id: Int? = null,


    var name: String,


    var email: String,


    var status: CustomerStatus
){



}
