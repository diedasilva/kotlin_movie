package com.example.kotlin_movie.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movie.data.models.Movie
import com.example.kotlin_movie.data.repository.MovieRepository

class FavoritesViewModel : ViewModel() {
    val likedMovies: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
}
