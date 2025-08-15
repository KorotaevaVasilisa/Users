package ru.vsls.users.presentation.screens.list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.vsls.users.domain.model.User

@Composable
fun ListScreen(modifier: Modifier, viewModel: ListViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    PullToRefreshCustomStyle(
        state.users,
        state.isLoading,
        viewModel::updateUsers,
        state.isEmpty,
        modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshCustomStyle(
    items: List<User>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    isEmpty: Boolean,
    modifier: Modifier = Modifier,
) {
    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier.fillMaxSize(),
        state = state,
        contentAlignment = Alignment.Center,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = state
            )
        },
    ) {
        if (isEmpty)
            Text("Обновите данные свайпом вниз")

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items, key = { it.id }) { item ->
                ListItem(
                    item.firstName,
                    item.lastName,
                    item.phone,
                    item.pictureThumbnail,
                    item.city,
                    {})
            }
        }
    }
}