package com.example.kinetic.presentation.genres.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage

@Composable
fun SingleGenre(
    image: String,
    title: String,
    gameCount: Int
) {
    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        AsyncImage(model = image, contentDescription = "image")
        Column {
            Text(text = title)
            Text(text = gameCount.toString())
        }
    }
}