package com.example.kinetic.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun GameCard(
    rating: Double,
    name: String,
    image: String,
    onclick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onclick() }
            .fillMaxWidth()
            .height(300.dp)
            .clip(shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.BottomCenter
        ){
            AsyncImage(model = image, contentDescription = "category image")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    tint = Color.Yellow,
                    imageVector = Icons.Default.Star, contentDescription = "rating")
                Text(
                    color = Color.White,
                    text = rating.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
        Text(
            modifier = Modifier.padding(5.dp),
            text = name,
        )
    }
}