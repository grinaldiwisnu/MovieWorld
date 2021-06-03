package com.grinaldi.movieworld.favorite.di

import com.grinaldi.movieworld.favorite.ui.movies.FavoriteMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMoviesViewModel(get()) }
}