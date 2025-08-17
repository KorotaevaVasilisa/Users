package ru.vsls.users.presentation.screens.details

import ru.vsls.users.domain.model.User

data class DetailsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null
)
