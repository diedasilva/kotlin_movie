package com.example.kotlin_movie.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_movie.R
import com.example.kotlin_movie.data.models.Movie

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun onLikeButtonClicked(movie: Movie) {
        viewModel.likeMovie(movie)
    }
}

