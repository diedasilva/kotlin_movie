package com.example.kotlin_movie.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_movie.R
import com.example.kotlin_movie.adapters.ListViewAdapter
import com.example.kotlin_movie.data.AppData
import com.example.kotlin_movie.data.models.Movie
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match

/**
 * Un fragment simple qui étend la classe [Fragment].
 * Utilisez la méthode de fabrique [MainFragment.newInstance]
 * pour créer une instance de ce fragment.
 */

class MainFragment : Fragment() {


    private lateinit var listView: ListView
    private lateinit var adapter: ListViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        listView = view.findViewById(R.id.listView)

        // Initialise un adaptateur ListViewAdapter avec une liste vide
        adapter = ListViewAdapter(requireContext(), emptyList())
        Log.d("MainFragment", "onCreateView: $adapter")

        // Associe l'adaptateur à la ListView
        listView.adapter = adapter

        // Utilise un LifecycleScope pour lancer une coroutine
        viewLifecycleOwner.lifecycleScope.launch {
            // Appelle la fonction getData() pour récupérer les données
            getData()
        }

        return view
    }

    // Fonction suspendue pour récupérer les données
    private suspend fun getData() {
        try {
            com.example.kotlin_movie.data.repository.MovieRepository.getData { movies ->
                // Appelle la fonction updateData() de l'adaptateur avec la liste de films reçue
                adapter.updateData(movies)
            }
        } catch (e: Exception) {
            Log.e("MainFragment", "Error fetching data", e)
        }
    }

}