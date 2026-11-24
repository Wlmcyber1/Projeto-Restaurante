package com.example.minhaprimeiraaplicao

import androidx.compose.runtime.Composable

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.minhaprimeiraaplicao.ui.theme.screen.Carrinho
import com.example.minhaprimeiraaplicao.ui.theme.screen.Inicio
import com.example.minhaprimeiraaplicao.ui.theme.screen.Painel
import com.example.minhaprimeiraaplicao.viewModel.PainelProdutoViewModel


@Composable
fun App() {
    val navController = rememberNavController()

    // ViewModel criado aqui em cima: Painel e Carrinho usam O MESMO objeto
    val painelViewModel: PainelProdutoViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.INICIO) {
        composable(Routes.INICIO) {
            Inicio(onIrParaPainel = { navController.navigate(Routes.PAINEL) })
        }
        composable(Routes.PAINEL) {
            Painel(
                viewModel = painelViewModel,
                onEscolherProduto = { id -> }
            )
        }
        composable(Routes.CARRINHO) {
            Carrinho(
                itens = emptyList(), // Parte 3: virá do painelViewModel
                onVoltar = { navController.popBackStack() },
                onEnviarParaCozinha = {
                    navController.navigate(Routes.INICIO) {
                        popUpTo(Routes.INICIO) { inclusive = true }
                    }
                }
            )
        }
    }


}
