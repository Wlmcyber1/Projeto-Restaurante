package com.example.minhaprimeiraaplicao.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//so monta quando precisa com o lazy
object RetrofitInstancia {
    const val base_URL = "http://localhost:8082/"
    val service: RestauranteService by lazy {
        Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create()) // converte o JSON do back em objetos Kotlin
            .build()
            .create(RestauranteService::class.java)
    }
}