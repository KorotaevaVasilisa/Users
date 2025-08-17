package ru.vsls.users.presentation.screens.details

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import ru.vsls.users.domain.model.User
import ru.vsls.users.presentation.items.InfoRow
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    id: String,
    viewModel: DetailsViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(id) {
        viewModel.loadLocalUser(id)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Пользователь ${state.user?.firstName}") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.error != null -> {
                    Text(
                        text = state.error ?: "Неизвестная ошибка",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                state.isLoading -> {
                    CircularProgressIndicator()
                }

                state.user != null -> {
                    UserDetailsContent(user = state.user!!)
                }
            }
        }
    }
}

@Composable
fun UserDetailsContent(user: User) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = user.pictureLarge,
            contentDescription = "Фото пользователя",
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = "${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = user.city,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(20.dp))
        InfoRow(label = "Пол:", value = user.gender.replaceFirstChar { it.uppercase() })
        InfoRow(label = "Дата рождения:", value = user.dobDateIso.take(10))
        InfoRow(label = "Возраст:", value = "${user.dobAge}")
        InfoRow(
            label = "E-mail:", value = user.email,
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = "mailto:${user.email}".toUri()
                context.startActivity(intent)
            })
        InfoRow(
            label = "Телефон:", value = user.phone,
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = "tel:${user.phone}".toUri()
                context.startActivity(intent)
            })
        InfoRow(
            label = "Адрес:",
            value = "${user.streetNumber} ${user.streetName}, ${user.city}, ${user.state}, ${user.country}",
            onClick = {
                val addr =
                    "${user.streetNumber} ${user.streetName}, ${user.city}, ${user.state}, ${user.country}"
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    "geo:0,0?q=${Uri.encode(addr)}".toUri()
                )
                context.startActivity(intent)
            }
        )
    }
}

