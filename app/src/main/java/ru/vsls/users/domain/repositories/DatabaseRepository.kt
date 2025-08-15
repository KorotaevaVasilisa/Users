package ru.vsls.users.domain.repositories

import ru.vsls.users.domain.model.User

interface DatabaseRepository {
    suspend fun getLocalUsers(): List<User>
    suspend fun deleteLocalUser()
    suspend fun insertNewUsers(users: List<User>)
}