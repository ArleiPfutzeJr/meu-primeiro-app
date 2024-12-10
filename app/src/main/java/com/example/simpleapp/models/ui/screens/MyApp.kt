package com.example.simpleapp.models.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpleapp.ui.screens.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            AnimatedTopAppBar() // TopAppBar animada
        }
    ) { paddingValues ->
        // Gerenciador de Navegação
        NavHost(
            navController = navController,
            startDestination = "homeScreen",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("homeScreen") { HomeScreen(navController) }
            composable("postScreen") { PostScreen(navController) }
            composable("commentsScreen") { CommentsScreen(navController) }
            composable("photoScreen") { PhotoScreen(navController) }
            composable("albumScreen") { AlbumScreen(navController) }
            composable("todoScreen") { TodoScreen(navController) }
            composable("userScreen") { UserScreen(navController) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedTopAppBar() {
    // Texto para exibição
    val message = "Desenvolvido por: Arlei Pfutze Jr / Unirriter     "
    val animationDelay = 150L
    var startIndex by remember { mutableStateOf(0) }

    // Animação temporizada
    LaunchedEffect(Unit) {
        while (true) {
            delay(animationDelay)
            startIndex = (startIndex + 1) % message.length
        }
    }

    TopAppBar(
        title = {
            Text(
                text = message.substring(startIndex) + message.substring(0, startIndex),
                maxLines = 1,
                overflow = TextOverflow.Clip, // Para evitar texto fora da barra
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // Cor de fundo da barra
            titleContentColor = MaterialTheme.colorScheme.onPrimary // Cor do texto
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}
