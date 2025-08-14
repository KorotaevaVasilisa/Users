package ru.vsls.users.data.remote

import ru.vsls.users.data.remote.api.UsersApiService
import ru.vsls.users.data.remote.mapper.toDomain
import ru.vsls.users.domain.model.User
import ru.vsls.users.domain.repositories.NetworkRepository

class NetworkRepositoryImpl(private val api: UsersApiService): NetworkRepository {
    override suspend fun getUsers(): List<User> {
        return api.getUsers().results.toDomain()
    }
}