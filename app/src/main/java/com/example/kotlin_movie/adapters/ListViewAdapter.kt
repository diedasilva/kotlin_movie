package com.example.kotlin_movie.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.kotlin_movie.R
import com.example.kotlin_movie.data.AppData
import com.example.kotlin_movie.data.MovieItem
import com.example.kotlin_movie.data.models.Movie
import com.example.kotlin_movie.ui.detail.DetailActivity

class ListViewAdapter(private val context: Context, var data: List<Movie>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val dataList: MutableList<Movie> = data.toMutableList()
    private val isItemLiked: MutableList<Boolean> = MutableList(data.size) { false }
    override fun getCount(): Int {
        return dataList.size
    }
    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    fun updateData(newData: List<Movie>) {
        dataList.clear()
        dataList.addAll(newData)
        isItemLiked.addAll(newData.map { movie ->
            AppData.instance.likedItems.any { it.id == movie.id }
        })
        notifyDataSetChanged()
        Log.d("ListViewAdapter", "updateData: $dataList")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        val movie = getItem(position) as Movie
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_layout, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.textView.text = movie.title
        val isLiked = isItemLiked[position]
        if (isLiked) {
            viewHolder.button.setBackgroundResource(R.drawable.ic_favorite)
        } else {
            viewHolder.button.setBackgroundResource(R.drawable.ic_favorite_border)
        }

        viewHolder.button.setOnClickListener {
            isItemLiked[position] = !isItemLiked[position]
            val item = MovieItem(movie.id, movie.title)

            if (isItemLiked[position]) {
                viewHolder.button.setBackgroundResource(R.drawable.ic_favorite)
                Toast.makeText(context, "L'item ${item.title} est liké", Toast.LENGTH_SHORT).show()
                AppData.instance.likedItems.add(item)
            } else {
                viewHolder.button.setBackgroundResource(R.drawable.ic_favorite_border)
                Toast.makeText(context, "L'item ${item.title} n'est pas liké", Toast.LENGTH_SHORT).show()
                AppData.instance.likedItems.remove(item)
            }
            Log.d("ListViewAdapter", "getViewLikedItems: ${AppData.instance.likedItems}")
            notifyDataSetChanged()
        }

        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayout)
        linearLayout.setOnClickListener {
            Log.d("ListViewAdapter", "getView: $movie")
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("itemId", movie.id)
            context.startActivity(intent)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val button: Button = view.findViewById(R.id.button)
        val textView: TextView = view.findViewById(R.id.textView)
    }
}
