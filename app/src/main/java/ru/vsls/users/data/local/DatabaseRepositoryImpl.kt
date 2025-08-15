package ru.vsls.users.data.local

import ru.vsls.users.data.local.dao.UserDao
import ru.vsls.users.data.local.mapper.toDomain
import ru.vsls.users.data.local.mapper.toEntity
import ru.vsls.users.domain.model.User
import ru.vsls.users.domain.repositories.DatabaseRepository

class DatabaseRepositoryImpl(private val dao: UserDao) : DatabaseRepository {
    override suspend fun getLocalUsers(): List<User> {
        return dao.getUsers().map { it.toDomain() }
    }

    override suspend fun deleteLocalUser() {
        dao.deleteUsers()
    }

    override suspend fun insertNewUsers(users: List<User>) {
        dao.insertUsers(users.map { it.toEntity() })
    }
}