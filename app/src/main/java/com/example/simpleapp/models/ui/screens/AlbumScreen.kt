package com.example.simpleapp.ui.screens

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
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Voltar para Home", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        // Título da tela
        Text(
            text = "Lista de Álbuns",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de álbuns
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre os cards
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
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Título do álbum
            Text(
                text = "Título: ${album.title}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // ID do álbum
            Text(
                text = "ID do Álbum: ${album.id}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
