package com.example.minhaprimeiraaplicao.model

data class Produto(
    val id: Long,
    val nome: String,
    val preco: Double,
    val descricao: String?,
    val imagemUrl: String?,
    val categoria: Categoria?
)
