package com.grinaldi.movieworld

import android.app.Application
import com.grinaldi.movieworld.core.di.coreModule
import com.grinaldi.movieworld.core.di.localModule
import com.grinaldi.movieworld.core.di.networkModule
import com.grinaldi.movieworld.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                coreModule,
                localModule,
                networkModule,
                viewModelModule
            )
        }
    }
}