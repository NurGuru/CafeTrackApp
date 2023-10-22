package ru.nurguru.data

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

import ru.nurguru.di.dataModule
import ru.nurguru.di.domainModule

class GuestApp: Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@GuestApp)
            modules(dataModule + domainModule)
        }
    }
}