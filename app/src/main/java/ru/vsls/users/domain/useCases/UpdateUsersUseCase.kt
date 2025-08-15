package ru.vsls.users.domain.useCases

import ru.vsls.users.domain.model.User
import ru.vsls.users.domain.repositories.DatabaseRepository
import ru.vsls.users.domain.repositories.NetworkRepository

interface UpdateUsersUseCase{
    suspend fun invoke():List<User>
}

class UpdateUsersUseCaseImpl(private val netRepository: NetworkRepository, private val databaseRepository: DatabaseRepository):
    UpdateUsersUseCase{
    override suspend fun invoke(): List<User> {
        val newUsers = netRepository.getUsers()
        databaseRepository.updateUsers(newUsers)
        return databaseRepository.getLocalUsers()
    }
}