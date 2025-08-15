package ru.vsls.users.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.vsls.users.presentation.screens.list.ListViewModel

val viewModelModule = module {
    viewModel{ ListViewModel(get()) }
}