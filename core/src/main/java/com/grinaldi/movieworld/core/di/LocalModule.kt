package com.grinaldi.movieworld.core.di

import androidx.room.Room
import com.grinaldi.movieworld.core.data.source.local.database.MovieDatabase
import com.grinaldi.movieworld.core.data.source.local.entities.GenreConverter
import com.grinaldi.movieworld.core.utils.Constant
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {

        val passphrase: ByteArray =
            SQLiteDatabase.getBytes("MOVIEWORLD_KEY_GENERATED".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, Constant.DB_NAME
        )
            .addTypeConverter(GenreConverter())
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    single {
        get<MovieDatabase>().movieDao()
    }
}