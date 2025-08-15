package ru.vsls.users.di


import org.koin.dsl.module
import ru.vsls.users.data.local.DatabaseRepositoryImpl
import ru.vsls.users.data.remote.NetworkRepositoryImpl
import ru.vsls.users.domain.repositories.DatabaseRepository
import ru.vsls.users.domain.repositories.NetworkRepository

val repositoryModule = module {
    //singleOf(::NetworkRepositoryImpl) bind NetworkRepository::class
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
    single<DatabaseRepository> { DatabaseRepositoryImpl(get()) }
}
