package com.example.minhaprimeiraaplicao.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.minhaprimeiraaplicao.model.Produto
import com.example.minhaprimeiraaplicao.network.RestauranteService
import com.example.minhaprimeiraaplicao.network.RetrofitInstancia

class PainelProdutoViewModel : ViewModel() {
//emptyList() -> retorna uma lista vazia e imutavel
    var listaProdutos by mutableStateOf < List<Produto>>(emptyList())
        private set

    var produtoSelecionado by mutableStateOf<Produto?>(null)
            private set

    suspend fun buscarProduto (){
        try {
            val response = RetrofitInstancia.service.listarProdutos()

            if (response.isSuccessful) {
                listaProdutos = response.body() ?: emptyList()
            }
        }
        catch (e: Exception){
            Log.e("PainelProduto", "Erro ao buscar produtos", e)
        }

    }

}