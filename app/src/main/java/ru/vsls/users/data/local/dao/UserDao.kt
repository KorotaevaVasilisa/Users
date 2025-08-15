package ru.vsls.users.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vsls.users.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>

    @Query("DELETE  FROM users")
    suspend fun deleteUsers()
}