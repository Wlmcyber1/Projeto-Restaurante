package com.example.minhaprimeiraaplicao.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Pedido(
    val id: Long,
    val resumoItens: String,
    val valorTotal: BigDecimal,
    val status: String,
    val dataCriacao: LocalDateTime,
    val categoria: Categoria
)
