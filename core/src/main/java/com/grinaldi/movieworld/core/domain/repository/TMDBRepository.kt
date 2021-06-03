package com.grinaldi.movieworld.core.domain.repository

import com.grinaldi.movieworld.core.domain.entities.Detail
import com.grinaldi.movieworld.core.domain.entities.Movie
import com.grinaldi.movieworld.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TMDBRepository {
    fun getMovieList(): Flow<Resource<List<Movie>>>

    fun getMovieDetail(id: Int): Flow<Resource<Detail>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun checkMovieFavorite(id: Int): Flow<Boolean>

    suspend fun insertFavoriteMovie(id: Int)

    suspend fun deleteFavoriteMovie(id: Int)

    fun getTvList(): Flow<Resource<List<Movie>>>

    fun getTvDetail(id: Int): Flow<Resource<Detail>>

    fun getFavoriteTv(): Flow<List<Movie>>
}