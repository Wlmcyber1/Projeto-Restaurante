package com.example.minhaprimeiraaplicao.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.minhaprimeiraaplicao.model.ItemCarrinho
import com.example.minhaprimeiraaplicao.model.Produto
import com.example.minhaprimeiraaplicao.network.RestauranteService
import com.example.minhaprimeiraaplicao.network.RetrofitInstancia

class PainelProdutoViewModel : ViewModel() {
//emptyList() -> retorna uma lista vazia e imutavel
    var listaProdutos by mutableStateOf < List<Produto>>(emptyList())
        private set

    var produtoSelecionado by mutableStateOf<Produto?>(null)
            private set

    var carrinho by mutableStateOf<List<ItemCarrinho>>(emptyList())
        private set

    fun adicionarAoCarrinho(produto: Produto) {
        val jaExiste = carrinho.any { it.produto.id == produto.id }

        carrinho = if (jaExiste) {
            // Mesmo prato de novo: só aumenta a quantidade
            carrinho.map {
                if (it.produto.id == produto.id) it.copy(quantidade = it.quantidade + 1) else it
            }
        } else {
            // Prato novo: entra na lista com quantidade 1
            carrinho + ItemCarrinho(produto, 1)
        }
    }

    fun removerDoCarrinho(produto: Produto) {
        carrinho = carrinho
            // Diminui 1 da quantidade daquele prato
            .map { if (it.produto.id == produto.id) it.copy(quantidade = it.quantidade - 1) else it }
            // Se chegou a 0, o prato sai da lista
            .filter { it.quantidade > 0 }
    }

    fun quantidadeDe(produto: Produto): Int =
        carrinho.find { it.produto.id == produto.id }?.quantidade ?: 0

    fun limparCarrinho() {
        carrinho = emptyList()
    }

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