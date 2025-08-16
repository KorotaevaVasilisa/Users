package ru.vsls.users.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.users.domain.useCases.GetUserByIdUseCase
import ru.vsls.users.presentation.screens.list.ListUiState

class DetailsViewModel(private val getUserUseCase: GetUserByIdUseCase): ViewModel() {
    private val _state = MutableStateFlow(DetailsUiState())
    val state = _state.asStateFlow()

    fun loadLocalUser(id:String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val user = getUserUseCase.invoke(id)
                    _state.value = DetailsUiState(user = user)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}