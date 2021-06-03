package com.grinaldi.movieworld.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.grinaldi.movieworld.core.domain.entities.Movie
import com.grinaldi.movieworld.core.utils.ImageUrl
import com.grinaldi.movieworld.databinding.ListMovieItemBinding

class MainAdapter(private val listener: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var movies = listOf<Movie>()

    fun setMovies(movies: List<Movie>?) {
        movies?.let {
            this.movies = it
            notifyDataSetChanged()
        }
    }

    inner class MainViewHolder(private val view: ListMovieItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(movie: Movie) {
            with(view) {
                title.text = movie.title
                userScore.text = movie.voteAverage.toString()
                moviePoster.load(ImageUrl.getThumbnailImageUrl(movie.posterPath)) {
                    crossfade(true)
                }
            }
            itemView.setOnClickListener { listener(movie) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.MainViewHolder {
        val view = ListMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}