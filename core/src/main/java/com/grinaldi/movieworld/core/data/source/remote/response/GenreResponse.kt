package com.grinaldi.movieworld.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("name")
    val name: String = ""
)