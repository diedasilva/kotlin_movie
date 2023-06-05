package com.example.kotlin_movie.ui.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.kotlin_movie.R
import com.example.kotlin_movie.adapters.ListViewAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    private val dataList: MutableList<String> = mutableListOf()
    fun updateData(data: List<String>) {
        dataList.clear()
        dataList.addAll(data)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val adapter = ListViewAdapter(requireContext(), emptyList())

        val likedItems = adapter.getLikedItems()
        Log.d("LikedItems", likedItems.toString())
        for (item in likedItems) {
            // Traiter chaque élément liké
            Log.d("LikedItems", item)
        }
        return view
    }

}