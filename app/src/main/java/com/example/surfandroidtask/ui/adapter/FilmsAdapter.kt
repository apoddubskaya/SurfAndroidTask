package com.example.surfandroidtask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.surfandroidtask.R
import com.example.surfandroidtask.data.Film

class FilmsAdapter: RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    private val films = ArrayList<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.film_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount() = films.size

    fun setData(data: List<Film>) {
        films.clear()
        films.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.film_title)
        private val description: TextView = itemView.findViewById(R.id.film_description)
        private val date: TextView = itemView.findViewById(R.id.film_date)

        fun bind(film: Film) {
            title.text = film.title
            description.text = film.description
            date.text = film.date

        }
    }
}