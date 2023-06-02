package com.example.kotlin_movie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movie.data.repository.MovieRepository
import com.example.kotlin_movie.data.models.Movie

class DetailViewModel : ViewModel() {
    val movie = MutableLiveData<Movie>()

    fun loadMovie(id: String) {
        movie.value = MovieRepository.getMovie(id)
    }

    fun likeMovie(movie: Movie) {
        movie.isLiked = !movie.isLiked
        MovieRepository.updateMovie(movie)
    }
}

