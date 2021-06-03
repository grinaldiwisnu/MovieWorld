package com.grinaldi.movieworld.core.domain.usecase

import androidx.lifecycle.LiveData
import com.grinaldi.movieworld.core.domain.entities.Detail
import com.grinaldi.movieworld.core.domain.entities.Movie
import com.grinaldi.movieworld.core.utils.Resource

interface UseCase {
    fun getAllMovies(): LiveData<Resource<List<Movie>>>
    fun getMovieDetail(id: Int): LiveData<Resource<Detail>>
    fun getFavoriteMovies(): LiveData<List<Movie>>
    fun checkMovieFavorite(id: Int): LiveData<Boolean>
    suspend fun addMovieToFavorite(id: Int)
    suspend fun removeMovieFromFavorite(id: Int)
    fun getAllTvShows(): LiveData<Resource<List<Movie>>>
    fun getTvShowDetail(id: Int): LiveData<Resource<Detail>>
    fun getFavoriteTvShows(): LiveData<List<Movie>>
}