package com.grinaldi.movieworld.core.domain.entities

data class Movie(
    val id: Int,
    val overview: String,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val movieType: Int,
    val isFavorite: Boolean = false
)
