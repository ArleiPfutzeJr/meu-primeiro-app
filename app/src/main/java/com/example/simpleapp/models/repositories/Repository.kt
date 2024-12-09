package com.example.simpleapp.repositories

import com.example.simpleapp.models.Post
import com.example.simpleapp.models.Comment
import com.example.simpleapp.models.Photo
import com.example.simpleapp.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call

class Repository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getPosts(): Call<List<Post>> {
        return apiService.getPosts()
    }
    fun getComments(): Call<List<Comment>> {
        return apiService.getComments()
    }
    fun getPhotos(): Call<List<Photo>> {
        return apiService.getPhotos()
    }
}
