package com.example.kotlin_movie.data.models

data class Movie(
    val id: String,
    val title: String,
    val description: String,
    var isLiked: Boolean = false
)
