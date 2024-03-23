package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import jakarta.persistence.*
import java.math.BigDecimal
import kotlin.jvm.Throws


@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

){

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO){
                throw  Exception("Não é possivel alterar um livro com status ${field}")
            }
            field = value
        }

    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerModel? = null,
                status: BookStatus?):this(id, name, price, customer){
                    this.status = status
                }

}