package com.example.kinetic.presentation.game_details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SinglePlatform(
    icon: Int? = null,
    name: String? = null
) {
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp)
    ){
        if (icon != null){
            Icon(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = icon),
                contentDescription = "windows")
        }
        if (name != null){
            Text(
                text = name,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}