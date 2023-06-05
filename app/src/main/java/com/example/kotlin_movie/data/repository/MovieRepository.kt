package com.example.kotlin_movie.data.repository

import android.util.Log
import com.example.kotlin_movie.data.models.Movie
import com.example.kotlin_movie.data.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
object MovieRepository {
    private val movies = mutableListOf<Movie>()
    private const val API_KEY = "986cc009b30512eb15a8cd91faa30a7c"

    fun getMovie(id: String): Movie? {
        val movieId = id.toIntOrNull()
        return if (movieId != null) {
            movies.find { it.id == movieId }
        } else {
            null
        }
    }
    fun updateMovie(movie: Movie) {
        val index = movies.indexOfFirst { it.id == movie.id }
        if (index != -1) {
            movies[index] = movie
        }
    }
    fun getData(onResult: (List<Movie>) -> Unit) {

        RetrofitService.tmdbApi.getPopularMovies(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    val movies = movieResponse?.results ?: emptyList()
                    val formattedMovies = movies.map { movieData ->
                        Movie(
                            id = movieData.id,
                            title = movieData.title,
                            overview = movieData.overview,
                            vote_average = movieData.vote_average,
                            poster_path = movieData.poster_path,
                            release_date = movieData.release_date,
                            isLiked = false
                        )
                    }
                    onResult(formattedMovies)
                } else {
                    Log.d("API_request_error", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("API_request_error", "Error: ${t.message}")
            }
        })
    }

    fun getMovieDetailsRepo(movieId: Int, onResult: (Movie?) -> Unit) {
        RetrofitService.tmdbApi.getMovieDetails(movieId, API_KEY).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val movie: Movie? = response.body()
                    onResult(movie)
                } else {
                    Log.d("API_request_error", "Error: ${response.code()}")
                    onResult(null)
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("API_request_error", "Error: ${t.message}")
                onResult(null)
            }
        })
    }
}
