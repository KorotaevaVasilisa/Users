package ru.vsls.users.presentation.screens.list

import ru.vsls.users.domain.model.User

data class ListUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val users: List<User> = emptyList(),
    val isEmpty: Boolean = users.isEmpty()
)
