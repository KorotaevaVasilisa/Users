package ru.vsls.users.presentation.screens.list

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.users.domain.repositories.NetworkRepository

class ListViewModel(private val repository: NetworkRepository): ViewModel() {
    private val _state = MutableStateFlow<ListUiState>(ListUiState())
    val state = _state.asStateFlow()

    init{
        loadRemoteUsers()
    }
    fun loadRemoteUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
          //  try{
                val users = repository.getUsers()
                _state.value = ListUiState(users = users)
         //   }
           // catch (e: Exception){
         //       _state.value = _state.value.copy(isLoading = false, error = e.message)
           // }
        }

    }
}