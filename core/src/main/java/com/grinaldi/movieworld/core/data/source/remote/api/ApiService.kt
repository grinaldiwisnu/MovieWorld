package com.grinaldi.movieworld.core.data.source.remote.api

import com.grinaldi.movieworld.core.BuildConfig
import com.grinaldi.movieworld.core.data.source.remote.response.DetailResponse
import com.grinaldi.movieworld.core.data.source.remote.response.ListMovieResponse
import com.grinaldi.movieworld.core.data.source.remote.response.ListTvResponse
import retrofit2.http.GET
import retrofit2.http.Path

const val apiKey: String = BuildConfig.API_KEY

interface ApiService {
    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    suspend fun getNowPlayingMovies(): ListMovieResponse

    @GET("tv/popular?api_key=$apiKey")
    suspend fun getPopularTv(): ListTvResponse

    @GET("movie/{movieId}?api_key=$apiKey")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): DetailResponse

    @GET("tv/{tvId}?api_key=$apiKey")
    suspend fun getTvDetail(@Path("tvId") tvId: Int): DetailResponse
}