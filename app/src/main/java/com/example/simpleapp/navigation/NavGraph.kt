package com.example.simpleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.simpleapp.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("second") { SecondScreen(navController) }
        composable("third") { ThirdScreen(navController) }
        composable("fourth") { FourthScreen(navController) }
    }
}
