package com.grinaldi.movieworld.core.data.source.local.entities

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class GenreConverter {

    @TypeConverter
    fun generateGenreListFromString(value: String?): List<String>? {
        return value?.let {
            val genres = it.split(',')
            return genres.map { genre -> genre }
        }
    }

    @TypeConverter
    fun generateStringFromGenreList(genres: List<String>?): String? {
        return genres?.joinToString { genre -> genre }
    }

}