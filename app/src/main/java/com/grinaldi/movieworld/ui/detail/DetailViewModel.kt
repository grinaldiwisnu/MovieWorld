package com.grinaldi.movieworld.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grinaldi.movieworld.core.domain.entities.Movie
import com.grinaldi.movieworld.core.domain.usecase.UseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: UseCase) : ViewModel() {
    fun getMovieDetail(id: Int) = useCase.getMovieDetail(id)

    fun getTvDetail(id: Int) = useCase.getTvShowDetail(id)

    fun checkIsMovieFavorite(id: Int): LiveData<Boolean> {
        return useCase.checkMovieFavorite(id)
    }

    fun addMovieToFavorite(movie: Movie) {
        viewModelScope.launch {
            useCase.addMovieToFavorite(movie.id)
        }
    }

    fun deleteMovieFromFavorite(movie: Movie) {
        viewModelScope.launch {
            useCase.removeMovieFromFavorite(movie.id)
        }
    }
}