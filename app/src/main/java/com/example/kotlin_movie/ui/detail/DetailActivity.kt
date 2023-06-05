package com.example.kotlin_movie.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin_movie.R
import com.example.kotlin_movie.data.models.Movie
import com.example.kotlin_movie.data.repository.MovieRepository
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val item = intent.getIntExtra("itemId", 0)
        getMovieDetails(item)
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

    }
    private fun getMovieDetails(movieId: Int) {
        MovieRepository.getMovieDetailsRepo(movieId) { movie ->
            if (movie != null) {
                // Traitez les détails du film ici
                displayMovieDetails(movie)
            } else {
                // Gérer l'échec de la récupération des détails du film
                Log.d("DetailActivity", "Failed to get movie details")
            }
        }
    }

    private fun displayMovieDetails(movie: Movie) {
        // Affichez les détails du film dans votre interface utilisateur
        val movieImageView = findViewById<ImageView>(R.id.movieImageView)
        val movieNameTextView = findViewById<TextView>(R.id.movieNameTextView)
        val releaseDateTextView = findViewById<TextView>(R.id.releaseDateTextView)
        val ratingTextView = findViewById<TextView>(R.id.ratingTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val posterimg = "https://image.tmdb.org/t/p/w500${movie.poster_path}"

        Picasso.get()
            .load(posterimg)
            .placeholder(R.drawable.ic_launcher_background) // Image par défaut en cas de chargement ou d'erreur
            .error(R.drawable.ic_launcher_background) // Image par défaut en cas d'erreur
            .fit()
            .centerCrop()
            .into(movieImageView)

        // Affichez les détails du film dans votre interface utilisateur
        movieNameTextView.text = movie.title
        releaseDateTextView.text = "Date de sortie : ${movie.release_date}"
        ratingTextView.text = "Note : ${movie.vote_average}"
        descriptionTextView.text = "Description : ${movie.overview}"
    }
}
