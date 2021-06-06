package com.grinaldi.movieworld.core.data

import com.grinaldi.movieworld.core.data.source.remote.api.ApiResponse
import com.grinaldi.movieworld.core.utils.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dataFromDb = populateDataDb().first()
        if (shouldFetch(dataFromDb)) {
            emit(Resource.Loading())
            when (val response = networkCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(response.data)
                    emitAll(populateDataDb().map {
                        Resource.Success(it)
                    })
                }

                is ApiResponse.Empty -> {
                    emitAll(populateDataDb().map {
                        Resource.Success(it)
                    })
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<ResultType>(response.errorMessage))
                }
            }
        } else {
            emitAll(populateDataDb().map {
                Resource.Success(
                    it
                )
            })
        }
    }

    fun build() = result

    protected abstract fun populateDataDb(): Flow<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract suspend fun networkCall(): Flow<ApiResponse<RequestType>>
    protected abstract suspend fun saveCallResult(data: RequestType)
}