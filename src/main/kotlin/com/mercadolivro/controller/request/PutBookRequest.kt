package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

data class PutBookRequest (
    var name: String?,

    var price: BigDecimal?,

    )
