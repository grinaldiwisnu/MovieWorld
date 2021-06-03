package com.grinaldi.movieworld.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("results")
    val movieResponses: List<MovieResponse> = listOf()
)