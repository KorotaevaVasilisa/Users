package ru.vsls.users.presentation.screens.list

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(viewModel: ListViewModel = koinViewModel ()){
    val state by viewModel.state.collectAsState()

    when{
        state.error!=null -> Text(text = state.error!!)
        state.isLoading -> CircularProgressIndicator()
        else ->{ Text(text = state.users.size.toString())}
    }
}