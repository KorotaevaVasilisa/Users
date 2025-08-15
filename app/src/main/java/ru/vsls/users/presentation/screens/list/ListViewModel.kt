package ru.vsls.users.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.users.domain.useCases.GetUsersUseCase
import ru.vsls.users.domain.useCases.UpdateUsersUseCase

class ListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val updateUsersUseCase: UpdateUsersUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ListUiState>(ListUiState())
    val state = _state.asStateFlow()

    init {
        loadLocalUsers()
    }

    fun loadLocalUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val users = getUsersUseCase.invoke()
                _state.value = ListUiState(users = users)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun updateUsers(){
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val users = updateUsersUseCase.invoke()
                _state.value = ListUiState(users = users)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}