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
import com.example.simpleapp.models.Comment
import com.example.simpleapp.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CommentsScreen(navController: NavController) {
    val context = LocalContext.current
    var comments by remember { mutableStateOf<List<Comment>>(emptyList()) }

    // Busca os comentários da API
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            Repository().getComments().enqueue(object : Callback<List<Comment>> {
                override fun onResponse(
                    call: Call<List<Comment>>,
                    response: Response<List<Comment>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            comments = it
                        }
                    } else {
                        Toast.makeText(context, "Falha ao carregar comentários", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
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
            onClick = { navController.navigate("postScreen") },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Voltar para Posts")
        }
        Text("Aqui estão os Comentários", modifier = Modifier.padding(bottom = 16.dp))

        // Exibe os comentários
        LazyColumn {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Nome: ${comment.name}", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
            Text(text = "Comentário: ${comment.body}", style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
        }
    }
}
