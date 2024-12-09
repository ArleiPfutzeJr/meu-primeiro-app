package com.example.simpleapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Models relacionados a API JsonPlaceHolder:", modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { navController.navigate("postScreen") }) {
            Text("Ver Posts")
        }
        Button(onClick = { navController.navigate("commentsScreen") }) {
            Text("Ver Comentarios")
        }
        Button(onClick = { navController.navigate("photoScreen") }) {
            Text("Ver Fotos")
        }
        Button(onClick = { navController.navigate("albumScreen") }) {
            Text("Ver Album")
        }
    }
}
