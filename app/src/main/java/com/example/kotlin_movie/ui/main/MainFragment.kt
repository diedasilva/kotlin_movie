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
import com.example.kotlin_movie.data.models.Movie
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    interface DataListener {
        fun onDataReceived(data: List<String>)
    }

    private lateinit var listView: ListView
    private lateinit var adapter: ListViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        listView = view.findViewById(R.id.listView)
        adapter = ListViewAdapter(requireContext(), emptyList())
        Log.d("MainFragment", "onCreateView: $adapter")
        listView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            getData()
        }

        return view
    }

    private suspend fun getData() {
        try {
            // Fetch data from MovieRepository
            com.example.kotlin_movie.data.repository.MovieRepository.getData { movies ->
                // Call the onDataReceived callback with the list of movies
                adapter.updateData(movies)
            }
        } catch (e: Exception) {
            // Handle any exceptions that occur
            Log.e("MainFragment", "Error fetching data", e)
        }
    }
}