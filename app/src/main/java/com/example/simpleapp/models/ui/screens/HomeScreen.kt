package com.example.simpleapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Layout principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Centraliza verticalmente
    ) {
        // Título da tela
        Text(
            text = "Projeto: Test API JsonPlaceHolder",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Subtítulo
        Text(
            text = "Modelos da API JsonPlaceHolder",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Botões
        HomeButton(
            text = "Posts",
            onClick = { navController.navigate("postScreen") }
        )
        HomeButton(
            text = "Comentários",
            onClick = { navController.navigate("commentsScreen") }
        )
        HomeButton(
            text = "Fotos",
            onClick = { navController.navigate("photoScreen") }
        )
        HomeButton(
            text = "Álbuns",
            onClick = { navController.navigate("albumScreen") }
        )
        HomeButton(
            text = "Todos",
            onClick = { navController.navigate("todoScreen") }
        )
        HomeButton(
            text = "Usuários",
            onClick = { navController.navigate("userScreen") }
        )
    }
}

@Composable
fun HomeButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp), // Cantos arredondados para um visual moderno
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Cor de fundo do botão
            contentColor = MaterialTheme.colorScheme.onPrimary // Cor do texto do botão
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
