package com.example.kotlin_movie.data.api

import com.example.kotlin_movie.data.models.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieAPI {
    @GET("movies")
    suspend fun getMovies(): Response<List<Movie>>

    companion object {
        operator fun invoke(): MovieAPI {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api-base.herokuapp.com/api/")
                .build()
                .create(MovieAPI::class.java)
        }
    }
}
