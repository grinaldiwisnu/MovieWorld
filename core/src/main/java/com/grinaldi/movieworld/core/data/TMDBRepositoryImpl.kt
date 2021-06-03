package com.grinaldi.movieworld.core.data

import com.grinaldi.movieworld.core.data.source.local.LocalDataSource
import com.grinaldi.movieworld.core.data.source.remote.RemoteDataSource
import com.grinaldi.movieworld.core.data.source.remote.api.ApiResponse
import com.grinaldi.movieworld.core.data.source.remote.response.DetailResponse
import com.grinaldi.movieworld.core.data.source.remote.response.ListMovieResponse
import com.grinaldi.movieworld.core.data.source.remote.response.ListTvResponse
import com.grinaldi.movieworld.core.domain.entities.Detail
import com.grinaldi.movieworld.core.domain.entities.Movie
import com.grinaldi.movieworld.core.domain.repository.TMDBRepository
import com.grinaldi.movieworld.core.utils.Constant
import com.grinaldi.movieworld.core.utils.Mapper
import com.grinaldi.movieworld.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class TMDBRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : TMDBRepository {
    override fun getMovieList(): Flow<Resource<List<Movie>>> {
        return object : NetworkResource<List<Movie>, ListMovieResponse>() {
            override fun populateDataDb(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { Mapper.movieEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?) = data.isNullOrEmpty()

            override suspend fun networkCall(): Flow<ApiResponse<ListMovieResponse>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: ListMovieResponse) {
                val mappedData = Mapper.movieResponseToEntity(data)
                localDataSource.insertMovie(mappedData)
            }
        }.build()
    }

    override fun getTvList(): Flow<Resource<List<Movie>>> {
        return object : NetworkResource<List<Movie>, ListTvResponse>() {
            override fun populateDataDb(): Flow<List<Movie>> {
                return localDataSource.getTvShows().map {
                    Mapper.movieEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?) = data.isNullOrEmpty()

            override suspend fun networkCall(): Flow<ApiResponse<ListTvResponse>> {
                return remoteDataSource.getTvList()
            }

            override suspend fun saveCallResult(data: ListTvResponse) {
                val mapped = Mapper.tvShowResponsesToEntity(data)
                localDataSource.insertMovie(mapped)
            }
        }.build()
    }

    override fun getMovieDetail(id: Int): Flow<Resource<Detail>> {
        return object : NetworkResource<Detail, DetailResponse>() {
            override fun populateDataDb(): Flow<Detail> {
                return localDataSource.getMovieDetail(id).mapNotNull {
                    if (it == null) {
                        Detail.createEmptyObject()
                    } else {
                        Mapper.detailEntityToDomain(it)
                    }
                }
            }

            override fun shouldFetch(data: Detail?) = data?.id == -1

            override suspend fun networkCall(): Flow<ApiResponse<DetailResponse>> {
                return remoteDataSource.getMovieDetail(id)
            }

            override suspend fun saveCallResult(data: DetailResponse) {
                val detail = Mapper.detailResponseToEntity(data, Constant.TYPE_MOVIE)
                localDataSource.insertDetailMovie(detail)
            }
        }.build()
    }

    override fun getTvDetail(id: Int): Flow<Resource<Detail>> {
        return object : NetworkResource<Detail, DetailResponse>() {
            override fun populateDataDb(): Flow<Detail> {
                return localDataSource.getTvShowDetail(id).map {
                    if (it == null) {
                        Detail.createEmptyObject()
                    } else {
                        Mapper.detailEntityToDomain(it)
                    }
                }
            }

            override fun shouldFetch(data: Detail?) = data?.id == -1

            override suspend fun networkCall(): Flow<ApiResponse<DetailResponse>> {
                return remoteDataSource.getTvDetail(id)
            }

            override suspend fun saveCallResult(data: DetailResponse) {
                val detail = Mapper.detailResponseToEntity(data, Constant.TYPE_TV_SHOW)
                localDataSource.insertDetailMovie(detail)
            }

        }.build()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            Mapper.movieEntityToDomain(it)
        }
    }

    override fun getFavoriteTv(): Flow<List<Movie>> {
        return localDataSource.getFavoriteTvShows().map {
            Mapper.movieEntityToDomain(it)
        }
    }

    override fun checkMovieFavorite(id: Int): Flow<Boolean> {
        return localDataSource.checkMovieFavorite(id)
    }

    override suspend fun insertFavoriteMovie(id: Int) {
        localDataSource.addFavoriteMovie(id)
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        localDataSource.deleteFavoriteMovie(id)
    }
}