package com.example.simpleapp.models
data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)