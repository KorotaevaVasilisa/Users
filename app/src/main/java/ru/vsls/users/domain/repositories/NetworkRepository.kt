package ru.vsls.users.domain.repositories

import ru.vsls.users.domain.model.User

interface NetworkRepository {
    suspend fun getUsers(): List<User>
}