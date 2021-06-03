package com.grinaldi.movieworld.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvResponse(
    @SerializedName("results")
    val tvShowResponses: List<TvResponse>
)
