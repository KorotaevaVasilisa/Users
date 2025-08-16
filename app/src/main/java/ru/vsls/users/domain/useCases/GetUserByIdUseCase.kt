package ru.vsls.users.domain.useCases

import ru.vsls.users.domain.model.User
import ru.vsls.users.domain.repositories.DatabaseRepository

interface GetUserByIdUseCase {
    suspend fun invoke(id: String): User?
}

class GetUserByIdUseCaseImpl(private val repository: DatabaseRepository) : GetUserByIdUseCase {
    override suspend fun invoke(id: String): User? {
        return repository.getLocalUser(id)
    }
}