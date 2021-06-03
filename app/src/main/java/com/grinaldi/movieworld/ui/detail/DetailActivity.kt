package com.grinaldi.movieworld.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.grinaldi.movieworld.R
import com.grinaldi.movieworld.core.domain.entities.Detail
import com.grinaldi.movieworld.core.domain.entities.Movie
import com.grinaldi.movieworld.core.utils.Constant
import com.grinaldi.movieworld.core.utils.ImageUrl
import com.grinaldi.movieworld.core.utils.Resource
import com.grinaldi.movieworld.databinding.ActivityDetailBinding
import com.grinaldi.movieworld.ui.adapter.GenreAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailEntity: Detail
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var collapsingToolbar: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        genreAdapter = GenreAdapter()
        val movieId = intent.getIntExtra(MOVIE_ID, 0)
        val movieType = intent.getIntExtra(
            MOVIE_TYPE,
            Constant.TYPE_MOVIE
        )
        var isMovieFavorite = false

        with(binding.rvMovieGenre) {
            layoutManager = LinearLayoutManager(
                this@DetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = genreAdapter
        }

        val data = detailViewModel.let {
            if (movieType == Constant.TYPE_MOVIE) it.getMovieDetail(
                movieId
            )
            else it.getTvDetail(movieId)
        }

        data.observe(this, {
            when (it) {
                is Resource.Success -> {
                    hideErrorMessage()
                    showLoading(false)
                    showData(it.data)
                    detailEntity = it.data as Detail
                }
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showErrorMessage(it.message as String)
                }
            }
        })

        detailViewModel.checkIsMovieFavorite(movieId).observe(this, {
            isMovieFavorite = it
            val icon =
                if (it) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            binding.fab.setImageResource(icon)
        })

        binding.fab.setOnClickListener {
            val movie = mapDetailToMovie(detailEntity, movieType)
            if (isMovieFavorite) {
                detailViewModel.deleteMovieFromFavorite(movie)
                Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show()
            } else {
                detailViewModel.addMovieToFavorite(movie)
                Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showData(detail: Detail?) {
        detail?.let {
            val rating = resources.getString(R.string.rating)
            with(binding) {
                collapseToolbarConfiguration(it.title)
                ivBigPoster.load(ImageUrl.getOriginalImageUrl(it.posterPath)) {
                    crossfade(true)
                }
                smallPoster.load(ImageUrl.getThumbnailImageUrl(it.posterPath)) {
                    crossfade(true)
                }
                tvTitle.text = it.title
//                moviePopularity.text = it.popularity.toString()
//                movieRating.text = String.format(rating, it.voteAverage)
                tvOverview.text = it.overview
                genreAdapter.setGenres(it.genres)
//                overviewLabel.visibility = View.VISIBLE
//                statusLabel.visibility = View.VISIBLE
//                homepageLabel.visibility = View.VISIBLE
//                popularityLabel.visibility = View.VISIBLE
//                ratingLabel.visibility = View.VISIBLE
            }
        }
    }

    private fun collapseToolbarConfiguration(name: String) {
        collapsingToolbar = findViewById(R.id.collapseToolbar)
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (binding.collapseToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapsingToolbar
                )
            ) {
                binding.collapseToolbar.setCollapsedTitleTextColor(Color.BLACK)
                binding.toolbar.apply {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    title = name
                    setTitleTextColor(Color.WHITE)
                    visibility = View.VISIBLE
                }
            } else {
                binding.collapseToolbar.setExpandedTitleColor(Color.BLACK)
                binding.toolbar.setBackgroundColor(Color.TRANSPARENT)
                binding.toolbar.visibility = View.GONE
            }
        })
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun showErrorMessage(message: String) {
        binding.fab.visibility = View.GONE
        binding.errorMessage.apply {
            visibility = View.VISIBLE
            text = message
        }
    }

    private fun hideErrorMessage() {
        binding.errorMessage.visibility = View.GONE
    }

    private fun showLoading(state: Boolean) {
        binding.fab.visibility = if (state) View.GONE else View.VISIBLE
        binding.progressBarDetail.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun mapDetailToMovie(detail: Detail, movieType: Int): Movie {
        return Movie(
            detail.id,
            detail.overview,
            detail.title,
            detail.posterPath,
            detail.voteAverage,
            movieType
        )
    }

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
        const val MOVIE_TYPE = "MOVIE_TYPE"
    }
}