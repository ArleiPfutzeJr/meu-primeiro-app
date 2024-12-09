package com.example.simpleapp.models.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpleapp.models.Post
import com.example.simpleapp.repositories.Repository
import com.example.simpleapp.ui.screens.CommentsScreen
import com.example.simpleapp.ui.screens.PostScreen
import com.example.simpleapp.ui.screens.HomeScreen
import com.example.simpleapp.ui.screens.PhotoScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Desenvolvimento: Arlei Pfutze Jr") }
            )
        }
    ) { paddingValues ->
        // Gerenciador de Navegação
        NavHost(
            navController = navController,
            startDestination = "homeScreen",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("homeScreen") {
                HomeScreen(navController)
            }
            composable("postScreen") {
                PostScreen(navController)
            }
            composable("commentsScreen") {
                CommentsScreen(navController)
            }
            composable("photoScreen") {
                PhotoScreen(navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}
