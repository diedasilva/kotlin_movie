package com.example.kotlin_movie.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin_movie.R
import com.example.kotlin_movie.data.AppData
import com.example.kotlin_movie.data.MovieItem
import com.example.kotlin_movie.ui.detail.DetailActivity

class FavoritesFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<MovieItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        listView = view.findViewById(R.id.listViewFavorites)

        adapter = object : ArrayAdapter<MovieItem>(requireContext(), android.R.layout.simple_list_item_1, ArrayList(AppData.instance.likedItems)) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                val movieItem = getItem(position)
                textView.text = getItem(position)?.title
                view.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("itemId", movieItem?.id)
                    context?.startActivity(intent)
                }
                return view
            }
        }
        listView.adapter = adapter
        return view
    }

    override fun onResume() {
        super.onResume()
        val newList = ArrayList(AppData.instance.likedItems)
        adapter.clear()
        adapter.addAll(newList)
        adapter.notifyDataSetChanged()
    }
}
