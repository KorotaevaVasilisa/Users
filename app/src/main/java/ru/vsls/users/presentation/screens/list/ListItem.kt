package ru.vsls.users.presentation.screens.list

import android.R.attr.contentDescription
import android.R.attr.name
import android.system.Os.link
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ListItem(
    name: String,
    surname: String,
    phone: String,
    link: String,
    address: String,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = {onItemClick()},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 4.dp
        ),
    ) {
        Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.Start) {
            AsyncImage(
                link,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            UserInfo(name, surname, phone, address)
        }
    }
}

@Composable
fun UserInfo(name: String, surname: String, phone: String, address: String) {
    Column(
        modifier = Modifier.padding(start = 8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "$name $surname")
        Text(text = phone)
        Text(text = address)
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
        address = "Address",
        onItemClick = {}
    )
}