package com.example.kotlin_movie.data.models

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Float,
    val poster_path: String,
    val release_date: String,
    var isLiked: Boolean = false
)