package com.example.kinetic.presentation.games_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kinetic.presentation.games_list.GamesListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesListScreen(
    viewModel: GamesListViewModel = hiltViewModel()
) {

    Scaffold {
        LazyColumn(){

        }
    }
}