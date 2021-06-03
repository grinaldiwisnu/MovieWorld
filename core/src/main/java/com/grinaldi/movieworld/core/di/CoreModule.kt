package com.grinaldi.movieworld.core.di

import com.grinaldi.movieworld.core.data.TMDBRepositoryImpl
import com.grinaldi.movieworld.core.data.source.local.LocalDataSource
import com.grinaldi.movieworld.core.data.source.remote.RemoteDataSource
import com.grinaldi.movieworld.core.domain.repository.TMDBRepository
import com.grinaldi.movieworld.core.domain.usecase.UseCase
import com.grinaldi.movieworld.core.domain.usecase.UseCaseImp
import org.koin.dsl.module

val coreModule = module {
    single<TMDBRepository> { TMDBRepositoryImpl(get(), get()) }

    single { RemoteDataSource(get()) }

    single { LocalDataSource(get()) }

    single<UseCase> { UseCaseImp(get()) }
}