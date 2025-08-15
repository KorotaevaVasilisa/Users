package ru.vsls.users.domain.repositories

import ru.vsls.users.domain.model.User

interface DatabaseRepository {
    suspend fun getLocalUsers(): List<User>
    suspend fun updateUsers(users: List<User>)
}