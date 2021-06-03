package com.grinaldi.movieworld.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grinaldi.movieworld.core.domain.entities.Genre
import com.grinaldi.movieworld.databinding.ListGenreItemBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    private var genreList: List<Genre> = listOf()

    fun setGenres(genreList: List<Genre>) {
        this.genreList = genreList
        this.notifyDataSetChanged()
    }

    inner class GenreViewHolder(private val view: ListGenreItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(genre: Genre) {
            view.genre.text = genre.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreAdapter.GenreViewHolder {
        val view = ListGenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreAdapter.GenreViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun getItemCount(): Int = genreList.size
}