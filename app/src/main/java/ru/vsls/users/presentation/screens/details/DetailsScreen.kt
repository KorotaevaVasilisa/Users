package ru.vsls.users.presentation.screens.details

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.vsls.users.domain.model.User
import ru.vsls.users.presentation.items.InfoRow
import androidx.core.net.toUri
import ru.vsls.users.R

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
                title = {
                    Text(
                        text = "${stringResource(R.string.user)} ${state.user?.firstName ?: ""}"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
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
                        text = state.error ?: stringResource(R.string.error),
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
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = user.pictureLarge,
            contentDescription = stringResource(R.string.user_foto),
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(24.dp))

        val infiniteTransition = rememberInfiniteTransition()
        val animatedColor by infiniteTransition.animateColor(
            initialValue = Color(0xFF60DDAD),
            targetValue = Color(0xFF2E6DD5),
            animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
            label = "color"
        )
        Text(
            text = "${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = animatedColor
        )
        Text(
            text = user.city,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(20.dp))
        InfoRow(
            label = stringResource(R.string.gender),
            value = user.gender.replaceFirstChar { it.uppercase() })
        InfoRow(label = stringResource(R.string.birthday), value = user.dobDateIso.take(10))
        InfoRow(label = stringResource(R.string.age), value = "${user.dobAge}")
        InfoRow(
            label = stringResource(R.string.e_mail), value = user.email,
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = "mailto:${user.email}".toUri()
                context.startActivity(intent)
            })
        InfoRow(
            label = stringResource(R.string.phone), value = user.phone,
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = "tel:${user.phone}".toUri()
                context.startActivity(intent)
            })
        InfoRow(
            label = stringResource(R.string.address),
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

