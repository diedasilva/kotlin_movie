package com.example.kotlin_movie.data.repository

import com.example.kotlin_movie.data.models.Movie

object MovieRepository {
    private val movies = mutableListOf<Movie>()

    init {
        for (i in 1..10) {
            movies.add(Movie("$i", "Film $i", "Description du film $i", false))
        }
    }

    fun getMovies(): List<Movie> = movies
    fun getMovie(id: String): Movie? = movies.find { it.id == id }
    fun getLikedMovies(): List<Movie> = movies.filter { it.isLiked }
    fun updateMovie(movie: Movie) {
        val index = movies.indexOfFirst { it.id == movie.id }
        if (index != -1) {
            movies[index] = movie
        }
    }
    fun addMovie(movie: Movie) {
        movies.add(movie)
    }
}
