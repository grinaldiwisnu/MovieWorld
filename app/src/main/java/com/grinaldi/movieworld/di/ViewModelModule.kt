package com.grinaldi.movieworld.di

import com.grinaldi.movieworld.ui.detail.DetailViewModel
import com.grinaldi.movieworld.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}