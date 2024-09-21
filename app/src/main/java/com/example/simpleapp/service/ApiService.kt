package com.example.simpleapp.network

import com.example.simpleapp.models.*
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("comments")
    fun getComments(): Call<List<Comment>>

    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>

    @GET("todos")
    fun getTodos(): Call<List<Todo>>

    @GET("users")
    fun getUsers(): Call<List<User>>
}
