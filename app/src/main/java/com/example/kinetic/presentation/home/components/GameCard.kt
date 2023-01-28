package com.example.kinetic.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    rating: Double,
    name: String,
    image: String,
    onclick: () -> Unit
) {
    Card(
        onClick = { onclick()},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(20.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            AsyncImage(
                contentScale = ContentScale.Crop,
                model = image,
                contentDescription = "category image")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.padding(2.dp),
                    tint = Color.Yellow,
                    imageVector = Icons.Default.Star, contentDescription = "rating")
                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.White,
                    text = rating.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
            Column(
                modifier = Modifier.graphicsLayer {
                alpha = 0.4F }
                    .align(Alignment.TopStart)
                    .background(Color.Black)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    color = Color.White,
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}