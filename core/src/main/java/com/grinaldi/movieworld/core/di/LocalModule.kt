package com.grinaldi.movieworld.core.di

import androidx.room.Room
import com.grinaldi.movieworld.core.data.source.local.database.MovieDatabase
import com.grinaldi.movieworld.core.data.source.local.entities.GenreConverter
import com.grinaldi.movieworld.core.utils.Constant
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, Constant.DB_NAME
        )
            .addTypeConverter(GenreConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<MovieDatabase>().movieDao()
    }
}