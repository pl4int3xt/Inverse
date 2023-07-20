package com.example.kinetic.presentation.genres.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kinetic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleGenre(
    onClick:() -> Unit,
    image: String,
    title: String,
    gameCount: Int
) {
    ElevatedCard(
        modifier = Modifier
            .height(300.dp)
            .padding(10.dp)
        ,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            AsyncImage(
                modifier = Modifier.height(200.dp),
                model = image,
                placeholder = painterResource(id = R.drawable.image_loading),
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 5.dp)
            ){
                Text(
                    textAlign = TextAlign.Start,
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    textAlign = TextAlign.Start,
                    text = gameCount.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}