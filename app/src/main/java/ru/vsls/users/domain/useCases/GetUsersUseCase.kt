package ru.vsls.users.domain.useCases

import ru.vsls.users.domain.model.User
import ru.vsls.users.domain.repositories.DatabaseRepository

interface GetUsersUseCase {
    suspend fun invoke():List<User>
}

class GetUsersUseCaseImpl(private val repository: DatabaseRepository) : GetUsersUseCase {
    override suspend fun invoke():List<User> {
        return repository.getLocalUsers()
    }
}