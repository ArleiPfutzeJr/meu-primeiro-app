package com.example.simpleapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simpleapp.models.Post
import com.example.simpleapp.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PostScreen(navController: NavController) {
    val context = LocalContext.current
    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }

    // Busca os posts da API
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            Repository().getPosts().enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            posts = it
                        }
                    } else {
                        Toast.makeText(context, "Falha ao carregar posts", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Toast.makeText(context, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { navController.navigate("homeScreen") },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Voltar para Home")
        }
        Button(
            onClick = { navController.navigate("commentsScreen") },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Voltar para Comentários")
        }
        Text("Posts:", modifier = Modifier.padding(bottom = 16.dp))

        // Exibe os posts
        LazyColumn {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Título: ${post.title}", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
            Text(text = "Corpo: ${post.body}", style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
        }
    }
}
