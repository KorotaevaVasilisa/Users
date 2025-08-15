package ru.vsls.users

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.vsls.users.di.databaseModule
import ru.vsls.users.di.networkModule
import ru.vsls.users.di.repositoryModule
import ru.vsls.users.di.viewModelModule


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(networkModule, repositoryModule, viewModelModule, databaseModule)
        }
    }
}