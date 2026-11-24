package com.example.minhaprimeiraaplicao.model

data class ItemCarrinho(
    val produto: Produto,
    val quantidade: Int
) {
    val subtotal: Double get() = produto.preco * quantidade
}
