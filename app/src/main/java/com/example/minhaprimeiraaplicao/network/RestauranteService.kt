package com.example.minhaprimeiraaplicao.network

import com.example.minhaprimeiraaplicao.model.Produto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestauranteService {
    @GET("produtos")
    suspend fun listarProdutos(): Response<List<Produto>>

    @GET("produtos/{id}")
    suspend fun listarProdutoPorId(@Path("id") id: Int) : Response<Produto>


}
