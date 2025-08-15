package ru.vsls.users.di

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.vsls.users.data.local.AppDatabase
import ru.vsls.users.data.local.dao.UserDao

fun provideDatabase(applicationContext: Context): AppDatabase {
    return Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "my-database"
    )
        .build()
}

fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { provideUserDao(get()) }
}