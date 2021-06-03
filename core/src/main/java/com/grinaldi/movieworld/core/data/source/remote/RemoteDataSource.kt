package com.grinaldi.movieworld.core.data.source.remote

import com.grinaldi.movieworld.core.data.source.remote.api.ApiResponse
import com.grinaldi.movieworld.core.data.source.remote.api.ApiService
import com.grinaldi.movieworld.core.data.source.remote.response.DetailResponse
import com.grinaldi.movieworld.core.data.source.remote.response.ListMovieResponse
import com.grinaldi.movieworld.core.data.source.remote.response.ListTvResponse
import com.grinaldi.movieworld.core.utils.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getMovies(): Flow<ApiResponse<ListMovieResponse>> {
        return flow {
            try {
                val data = apiService.getNowPlayingMovies()
                if (data.movieResponses.isEmpty())
                    emit(ApiResponse.Empty)
                else
                    emit(ApiResponse.Success(data))
            } catch (error: Exception) {
                emit(ApiResponse.Error(ErrorHandler.getErrorMessage(error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(id: Int): Flow<ApiResponse<DetailResponse>> {
        return flow {
            try {
                val data = apiService.getMovieDetail(id)
                emit(ApiResponse.Success(data))
            } catch (error: Exception) {
                emit(ApiResponse.Error(ErrorHandler.getErrorMessage(error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvList(): Flow<ApiResponse<ListTvResponse>> {
        return flow {
            try {
                val data = apiService.getPopularTv()
                if (data.tvShowResponses.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(data))
                }
            } catch (error: Exception) {
                emit(ApiResponse.Error(ErrorHandler.getErrorMessage(error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvDetail(id: Int): Flow<ApiResponse<DetailResponse>> {
        return flow {
            try {
                val data = apiService.getTvDetail(id)
                emit(ApiResponse.Success(data))
            } catch (error: Exception) {
                emit(ApiResponse.Error(ErrorHandler.getErrorMessage(error)))
            }
        }.flowOn(Dispatchers.IO)
    }
}