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
import com.example.simpleapp.models.Album
import com.example.simpleapp.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AlbumScreen(navController: NavController) {
    var albums by remember { mutableStateOf<List<Album>>(emptyList()) }

    // Busca os álbuns da API
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            Repository().getAlbums().enqueue(object : Callback<List<Album>> {
                override fun onResponse(
                    call: Call<List<Album>>,
                    response: Response<List<Album>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            albums = it
                        }
                    }
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
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
            text = "Lista de Álbuns",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Exibe os álbuns
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre itens
        ) {
            items(albums) { album ->
                AlbumItem(album)
            }
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
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
                text = "Título: ${album.title}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "ID do Álbum: ${album.id}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}
