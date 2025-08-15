package ru.vsls.users.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vsls.users.data.local.dao.UserDao
import ru.vsls.users.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}