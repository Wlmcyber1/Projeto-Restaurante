package com.example.minhaprimeiraaplicao

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.minhaprimeiraaplicao.ui.theme.screen.Inicio
import com.example.minhaprimeiraaplicao.ui.theme.screen.Painel


@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.INICIO) {
        composable(Routes.INICIO) {
            Inicio(onIrParaPainel = { navController.navigate(Routes.PAINEL) })
        }
        composable ( Routes.PAINEL ){
            Painel( onEscolherProduto = {id -> })
        }

    }


}