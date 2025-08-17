package ru.vsls.users.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.vsls.users.R
import ru.vsls.users.presentation.items.InfoRow

@Composable
fun ListItem(
    name: String,
    surname: String,
    phone: String,
    link: String,
    city: String,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = { onItemClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 4.dp
        ),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                link,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.user_foto)
            )
            UserInfo(name, surname, phone, city)
        }
    }
}

@Composable
fun UserInfo(name: String, surname: String, phone: String, city: String) {
    Column(
        modifier = Modifier.padding(start = 8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "${name} ${surname}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        InfoRow(label = stringResource(R.string.phone), value = phone)
        InfoRow(label = stringResource(R.string.city), value = city)
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    ListItem(
        name = "Name",
        surname = "Surname",
        phone = "Phone",
        link = "https://example.com/image.jpg",
        city = "Address",
        onItemClick = {}
    )
}