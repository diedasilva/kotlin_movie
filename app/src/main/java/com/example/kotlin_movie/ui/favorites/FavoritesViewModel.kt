package com.example.kotlin_movie.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movie.data.models.Movie
import com.example.kotlin_movie.data.repository.MovieRepository

class FavoritesViewModel : ViewModel() {
    private val _likedMovies = MutableLiveData<List<Movie>>()
    val likedMovies: LiveData<List<Movie>> = _likedMovies

    init {
        _likedMovies.value = MovieRepository.getLikedMovies()
    }
}
