package com.example.simpleapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.simpleapp.models.Todo
import com.example.simpleapp.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TodoScreen(navController: NavController) {
    var todos by remember { mutableStateOf<List<Todo>>(emptyList()) }

    // Busca as tarefas da API
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            Repository().getTodos().enqueue(object : Callback<List<Todo>> {
                override fun onResponse(
                    call: Call<List<Todo>>,
                    response: Response<List<Todo>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            todos = it
                        }
                    }
                }

                override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                    // Lida com falhas na API (opcional)
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
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Voltar para Home")
        }
        Text(
            text = "Lista de Tarefas (TODOs)",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Exibe a lista de tarefas
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre itens
        ) {
            items(todos) { todo ->
                TodoItem(todo)
            }
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Título: ${todo.title}", // Prefixo "Título: "
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "ID da Tarefa: ${todo.id}", // Exibe o ID da tarefa
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = if (todo.completed) "Status: Concluído" else "Status: Pendente", // Status
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (todo.completed) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red
            )
        }
    }
}
