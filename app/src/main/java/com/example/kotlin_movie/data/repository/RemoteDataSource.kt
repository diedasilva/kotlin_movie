package com.example.kotlin_movie.data

import com.example.kotlin_movie.data.api.MovieAPI
import com.example.kotlin_movie.data.models.Movie
import com.example.kotlin_movie.data.repository.MovieRepository

class RemoteDataSource(private val movieAPI: MovieAPI) {

    suspend fun getMovies(): List<Movie> {
        val response = movieAPI.getMovies()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }
}
