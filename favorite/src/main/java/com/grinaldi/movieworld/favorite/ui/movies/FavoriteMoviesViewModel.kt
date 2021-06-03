package com.grinaldi.movieworld.favorite.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.grinaldi.movieworld.core.domain.entities.Movie
import com.grinaldi.movieworld.core.domain.usecase.UseCase

class FavoriteMoviesViewModel(private val useCase: UseCase) : ViewModel() {
    private val errorMessage = MutableLiveData<String>()

    fun getErrorMessage(): LiveData<String> = errorMessage

    fun getFavoriteMovieList(): LiveData<List<Movie>> {
        return Transformations.map(useCase.getFavoriteMovies()) {
            val message = if (it.isEmpty()) "Data not found" else ""
            errorMessage.postValue(message)
            it
        }
    }

    fun getFavoriteTvShowList(): LiveData<List<Movie>> {
        return Transformations.map(useCase.getFavoriteTvShows()) {
            val message = if (it.isEmpty()) "Data not found" else ""
            errorMessage.postValue(message)
            it
        }
    }
}