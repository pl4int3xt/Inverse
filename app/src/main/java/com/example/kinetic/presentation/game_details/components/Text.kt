package com.example.kinetic.presentation.game_details.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.content.res.Configuration.CONTENTS_FILE_DESCRIPTOR
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDark() {
    Scaffold(
        containerColor = Color.Black
    ) {
        Text(
            text = "gags",
            color = Color.White
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun TextDarkpre() {
    TextDark()
}