package com.grinaldi.movieworld.core.domain.usecase

import androidx.lifecycle.asLiveData
import com.grinaldi.movieworld.core.domain.repository.TMDBRepository

class UseCaseImp(private val repository: TMDBRepository) : UseCase {
    override fun getAllMovies() = repository.getMovieList().asLiveData()

    override fun getMovieDetail(id: Int) = repository.getMovieDetail(id).asLiveData()

    override fun getFavoriteMovies() = repository.getFavoriteMovies().asLiveData()

    override fun checkMovieFavorite(id: Int) =
        repository.checkMovieFavorite(id).asLiveData()

    override suspend fun addMovieToFavorite(id: Int) {
        repository.insertFavoriteMovie(id)
    }

    override suspend fun removeMovieFromFavorite(id: Int) {
        repository.deleteFavoriteMovie(id)
    }

    override fun getAllTvShows() = repository.getTvList().asLiveData()

    override fun getTvShowDetail(id: Int) = repository.getTvDetail(id).asLiveData()

    override fun getFavoriteTvShows() = repository.getFavoriteTv().asLiveData()
}
