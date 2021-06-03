package com.grinaldi.movieworld.core.data.source.local

import com.grinaldi.movieworld.core.data.source.local.database.MovieDao
import com.grinaldi.movieworld.core.data.source.local.entities.DetailEntity
import com.grinaldi.movieworld.core.data.source.local.entities.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource(private val movieDao: MovieDao) {
    fun getMovies(): Flow<List<MovieEntity>> {
        return movieDao.getMovies()
    }

    fun getMovieDetail(id: Int): Flow<DetailEntity?> {
        return movieDao.getMovieDetail(id)
    }

    suspend fun insertMovie(movies: List<MovieEntity>) {
        withContext(Dispatchers.IO) { movieDao.insertMovie(movies) }
    }

    suspend fun insertDetailMovie(movieDetail: DetailEntity) {
        withContext(Dispatchers.IO) { movieDao.insertDetail(movieDetail) }
    }

    fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

    fun checkMovieFavorite(id: Int): Flow<Boolean> {
        return movieDao.checkMovieFavorite(id)
    }

    suspend fun addFavoriteMovie(id: Int) {
        withContext(Dispatchers.IO) { movieDao.addFavorite(id) }
    }

    suspend fun deleteFavoriteMovie(id: Int) {
        withContext(Dispatchers.IO) { movieDao.removeFavorite(id) }
    }

    fun getTvShows(): Flow<List<MovieEntity>> {
        return movieDao.getTvs()
    }

    fun getTvShowDetail(id: Int): Flow<DetailEntity?> {
        return movieDao.getTvDetail(id)
    }

    fun getFavoriteTvShows(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteTv()
    }

}