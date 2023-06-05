package com.example.kotlin_movie.data

data class MovieItem(val id: Int, val title: String)

class AppData private constructor() {
    companion object {
        val instance = AppData()
    }
    val likedItems: MutableList<MovieItem> = mutableListOf()
}
