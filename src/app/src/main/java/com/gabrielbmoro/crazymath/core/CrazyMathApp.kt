package com.gabrielbmoro.crazymath.core

import android.app.Application
import com.gabrielbmoro.crazymath.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

open class CrazyMathApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CrazyMathApp)
            koin.loadModules(listOf(helpersModule, repositoryModule, useCaseModule, viewModelModule))
            koin.createRootScope()
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}