package com.example.gymcompose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun GymsScreen(onItemClick: (id: Int) -> Unit) {
    val viewModel: GymViewModel = viewModel()
    val gymList = viewModel.state.observeAsState().value ?: mutableListOf()

    LazyColumn {
        items(gymList) { gym ->
            GymItem(gym = gym,
                onFavouriteIconClick = { viewModel.toggleFavorite(gym.id) },
                onItemClick = { onItemClick(gym.id) })
        }
    }
}

@Composable
fun GymItem(gym: Gym, onFavouriteIconClick: (Int) -> Unit, onItemClick: () -> Unit) {
    val icon = if (gym.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Card(elevation = 4.dp, modifier = Modifier
        .padding(8.dp)
        .clickable { onItemClick() }) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            DefaultIcon(
                Icons.Filled.Place, Modifier.weight(0.15f), contentDescription = "Location"
            )
            GymDetails(gym, Modifier.weight(0.70f))
            DefaultIcon(
                icon, Modifier.weight(0.15f), contentDescription = "Favorite"
            ) { onFavouriteIconClick(gym.id) }
        }
    }
}

@Composable
fun DefaultIcon(
    icon: ImageVector, modifier: Modifier, contentDescription: String, onClick: () -> Unit = {}
) {
    Image(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() },
        colorFilter = ColorFilter.tint(Color.DarkGray)
    )
}

@Composable
fun GymDetails(
    gym: Gym, modifier: Modifier, horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(
            text = gym.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary
        )
        CompositionLocalProvider(
            LocalContentAlpha.provides(ContentAlpha.medium)
        ) {
            Text(
                text = gym.location,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}