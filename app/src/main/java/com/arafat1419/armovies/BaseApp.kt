package com.arafat1419.armovies

import android.app.Application
import com.arafat1419.armovies.core.di.networkModule
import com.arafat1419.armovies.core.di.repositoryModule
import com.arafat1419.armovies.core.di.useCaseModule
import com.arafat1419.armovies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApp)

            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}