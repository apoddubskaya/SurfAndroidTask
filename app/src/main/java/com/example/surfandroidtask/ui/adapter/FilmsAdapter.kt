package com.example.surfandroidtask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.surfandroidtask.R
import com.example.surfandroidtask.data.Film
import com.example.surfandroidtask.databinding.FilmListItemBinding

class FilmsAdapter(
    private val onFilmClickListener: (String) -> Unit,
    private val onFavouriteCheckedChange: (Int, Boolean) -> Unit
): RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    private val films = ArrayList<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding).apply {
            itemView.setOnClickListener {
                onFilmClickListener(films[adapterPosition].title)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(films[position], onFavouriteCheckedChange)
    }

    override fun getItemCount() = films.size

    fun setData(data: List<Film>) {
        films.clear()
        films.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: FilmListItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film, onFavouriteCheckedChange: (Int, Boolean) -> Unit) {
            binding.apply {
                filmTitle.text = film.title
                filmDescription.text = film.description
                filmDate.text = film.date
                Glide.with(filmPicture.context)
                    .load(film.posterPath)
                    .placeholder(R.drawable.placeholder_poster)
                    .into(filmPicture)
                filmIsFavourite.setOnCheckedChangeListener(null)
                filmIsFavourite.isChecked = film.isFavourite
                filmIsFavourite.setOnCheckedChangeListener { _, checked ->
                    film.isFavourite = checked
                    onFavouriteCheckedChange(film.id, checked)
                }
            }
        }
    }
}