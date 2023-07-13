package com.example.kinetic.presentation.genres.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleGenre(
    image: String,
    title: String,
    gameCount: Int
) {
    ElevatedCard(
        modifier = Modifier.height(300.dp)
            .padding(16.dp)
        ,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            AsyncImage(
                modifier = Modifier.height(250.dp),
                model = image,
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
            Text(
                textAlign = TextAlign.Start,
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Text(
                textAlign = TextAlign.Start,
                text = gameCount.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }
}