package com.example.simpleapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.simpleapp.models.Photo
import com.example.simpleapp.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PhotoScreen(navController: NavController) {
    val context = LocalContext.current
    var photos by remember { mutableStateOf<List<Photo>>(emptyList()) }

    // Busca as fotos da API
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            Repository().getPhotos().enqueue(object : Callback<List<Photo>> {
                override fun onResponse(
                    call: Call<List<Photo>>,
                    response: Response<List<Photo>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            photos = it
                        }
                    } else {
                        Toast.makeText(context, "Falha ao carregar fotos", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
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
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Voltar para Home")
        }
        Text("Galeria de Fotos:", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))

        // Exibe as fotos
        LazyColumn {
            items(photos) { photo ->
                PhotoItem(photo)
            }
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Usando cardElevation corretamente
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(data = photo.url),
                contentDescription = "Imagem de ${photo.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = photo.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
