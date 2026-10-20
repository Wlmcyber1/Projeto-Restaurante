package com.example.minhaprimeiraaplicao

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.minhaprimeiraaplicao.ui.theme.Inicio

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rotes.INICIO) {
        composable(Rotes.INICIO) {
            Inicio(onIrParaPainel = { navController.navigate(Rotes.PAINEL) })
        }

    }


}