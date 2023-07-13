package com.example.kinetic.presentation.genres.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleGenre(
    image: String,
    title: String,
    gameCount: Int
) {
    OutlinedCard(onClick = { /*TODO*/ }) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            AsyncImage(model = image, contentDescription = "image")
            Column {
                Text(text = title)
                Text(text = gameCount.toString())
            }
        }
    }
}