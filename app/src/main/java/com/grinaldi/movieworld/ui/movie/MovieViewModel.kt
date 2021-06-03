package com.grinaldi.movieworld.ui.movie

import androidx.lifecycle.ViewModel
import com.grinaldi.movieworld.core.domain.usecase.UseCase

class MovieViewModel(private val useCase: UseCase) : ViewModel() {
    fun getMovies() = useCase.getAllMovies()
    fun getTv() = useCase.getAllTvShows()
}