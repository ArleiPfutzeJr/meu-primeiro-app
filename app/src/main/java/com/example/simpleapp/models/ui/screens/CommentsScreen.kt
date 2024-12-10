package com.example.simpleapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    // Layout principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botão para voltar à Home
        Button(
            onClick = { navController.navigate("homeScreen") },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Voltar para Home", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        // Título da tela
        Text(
            text = "Lista de Comentários",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de comentários em cards
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre itens
        ) {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Nome: ${comment.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Comentário: ${comment.body}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
