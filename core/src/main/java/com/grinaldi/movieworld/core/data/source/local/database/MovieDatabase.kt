package com.grinaldi.movieworld.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grinaldi.movieworld.core.data.source.local.entities.DetailEntity
import com.grinaldi.movieworld.core.data.source.local.entities.GenreConverter
import com.grinaldi.movieworld.core.data.source.local.entities.MovieEntity

@Database(entities = [MovieEntity::class, DetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}