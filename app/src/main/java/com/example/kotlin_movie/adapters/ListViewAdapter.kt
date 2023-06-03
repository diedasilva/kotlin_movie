package com.example.kotlin_movie.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.kotlin_movie.R
import com.example.kotlin_movie.ui.detail.DetailActivity

class ListViewAdapter(private val context: Context, private val data: List<String>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val dataList: List<String> = data
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_layout, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position) as String

        viewHolder.textView.text = item
        val isLiked = isItemLiked[position]
        if (isLiked) {
            viewHolder.button.setBackgroundResource(R.drawable.ic_favorite)
        } else {
            viewHolder.button.setBackgroundResource(R.drawable.ic_favorite_border)
        }

        viewHolder.button.setOnClickListener {
            isItemLiked[position] = !isItemLiked[position]
            notifyDataSetChanged()
        }

        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayout)
        linearLayout.setOnClickListener {
            // Gérer le clic sur le LinearLayout
            // Vous pouvez accéder à l'ID du TextView ici si nécessaire
            val textViewId = viewHolder.textView.id
            val item = getItem(position) as String
            val message = "Vous avez cliqué sur l'élément : $item"

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("item", item)
            context.startActivity(intent)

            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            // Lancer une nouvelle activité ou un fragment avec l'ID du TextView
        }

        return view
    }

    private class ViewHolder(view: View) {
        val button: Button = view.findViewById(R.id.button)
        val textView: TextView = view.findViewById(R.id.textView)
    }
}
