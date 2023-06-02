package com.example.kotlin_movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movie.data.models.Movie
import com.example.kotlin_movie.data.repository.MovieRepository

class MainViewModel : ViewModel() {
    private val _data = MutableLiveData<List<String>>()
    val data: LiveData<List<String>> = _data

    init {
        // Charger les données initiales depuis une source de données (par exemple, une API)
        loadData()
    }

    private fun loadData() {
        // Simuler le chargement des données depuis une source de données (par exemple, une API)
        val newData = listOf("Item 1", "Item 2", "Item 3")
        _data.value = newData
    }
}
