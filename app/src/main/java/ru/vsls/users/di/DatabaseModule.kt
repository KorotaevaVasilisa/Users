package ru.vsls.users.di

import androidx.room.Room
import org.koin.dsl.module
import ru.vsls.users.MyApplication
import ru.vsls.users.data.local.AppDatabase
import ru.vsls.users.data.local.dao.UserDao
import kotlin.jvm.java

fun provideDatabase(application: MyApplication): AppDatabase {
    return Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "my-database"
    )
        .build()
}

fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

val databaseModule = module {
    single { provideDatabase(get()) }
    single { provideUserDao(get()) }
}