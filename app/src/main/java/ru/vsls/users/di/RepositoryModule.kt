package ru.vsls.users.di


import org.koin.dsl.module
import ru.vsls.users.data.local.DatabaseRepositoryImpl
import ru.vsls.users.data.remote.NetworkRepositoryImpl
import ru.vsls.users.domain.repositories.DatabaseRepository
import ru.vsls.users.domain.repositories.NetworkRepository
import ru.vsls.users.domain.useCases.GetUsersUseCase
import ru.vsls.users.domain.useCases.GetUsersUseCaseImpl
import ru.vsls.users.domain.useCases.UpdateUsersUseCase
import ru.vsls.users.domain.useCases.UpdateUsersUseCaseImpl

val repositoryModule = module {
    //singleOf(::NetworkRepositoryImpl) bind NetworkRepository::class
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
    single<DatabaseRepository> { DatabaseRepositoryImpl(get()) }
    single<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
    single<UpdateUsersUseCase> { UpdateUsersUseCaseImpl(get(), get()) }
}
