package com.grinaldi.movieworld.core.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grinaldi.movieworld.core.utils.Constant

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val overview: String,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val movieType: Int = Constant.TYPE_MOVIE,
    val isFavorite: Boolean = false
)