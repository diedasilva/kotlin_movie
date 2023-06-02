package com.example.kotlin_movie.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.kotlin_movie.R
import com.example.kotlin_movie.adapters.ListViewAdapter

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ListViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        listView = view.findViewById(R.id.listView)
        adapter = ListViewAdapter(requireContext(), getData())
        listView.adapter = adapter

        return view
    }
    private fun getData(): List<String> {
        val dataList = mutableListOf<String>()
        for (i in 1..10) {
            dataList.add("Item $i")
        }
        return dataList
    }
}